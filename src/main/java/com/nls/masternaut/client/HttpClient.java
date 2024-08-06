package com.nls.masternaut.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nls.masternaut.Configuration;
import com.nls.masternaut.MasternautError;
import com.nls.masternaut.MasternautException;
import com.nls.masternaut.MasternautServerException;
import com.nls.masternaut.util.ObjectMapperFactory;
import com.nls.masternaut.util.RateLimiter;
import com.nls.masternaut.util.RequestParameterMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class HttpClient implements IClient {
    private static final int TIMEOUT_MILLIS = -1;

    private final RequestParameterMapper parameterMapper = new RequestParameterMapper();
    private final ObjectMapper objectMapper = ObjectMapperFactory.make();
    private final Configuration configuration;
    private final CloseableHttpClient httpClient;
    private final ThreadLocal<HttpClientContext> httpContext = new ThreadLocal<>();
    private final RateLimiter rateLimiter;

    public HttpClient(Configuration configuration) {
        this.configuration = configuration;
        this.httpClient = makeHttpClient(configuration);
        this.rateLimiter = new RateLimiter(configuration.getRequestsPerSecond(), configuration.getRequestBurstSize());
    }

    @Override
    public <T> T get(String path, Object parameters, TypeReference<T> responseType) {
        return executeAndTransform(new HttpGet(getUri(path, parameters)), responseType);
    }

    @Override
    public <T> T get(String path, Object parameters, Class<T> responseType) {
        return executeAndTransform(new HttpGet(getUri(path, parameters)), responseType);
    }

    @Override
    public <T> T post(String path, Object data, Class<T> responseType) {
        HttpPost request = setPayload(new HttpPost(getUri(path, null)), data);
        return executeAndTransform(request, responseType);
    }

    @Override
    public <T> T put(String path, Object data, Class<T> responseType) {
        HttpPut request = setPayload(new HttpPut(getUri(path, null)), data);
        return executeAndTransform(request, responseType);
    }

    @Override
    public <T> T delete(String path, Object parameters, Class<T> responseType) {
        return executeAndTransform(new HttpDelete(getUri(path, parameters)), responseType);
    }

    private <T> T executeAndTransform(HttpUriRequest request, Class<T> responseType) {
        String content = null;
        try {
            content = execute(request);
            return content == null ? null : objectMapper.readValue(content, responseType);
        } catch (IOException e) {
            throw throwError(content, e);
        }
    }

    private <T> T executeAndTransform(HttpUriRequest request, TypeReference<T> responseType) {
        String content = null;
        try {
            content = execute(request);
            return content == null ? null : objectMapper.readValue(content, responseType);
        } catch (IOException e) {
            throw throwError(content, e);
        }
    }

    private String execute(HttpUriRequest request) throws IOException {
        if (configuration.isBlockTillRateLimitReset()) {
            rateLimiter.blockTillRateLimitReset();
        }

        String encoding = Base64.getEncoder().encodeToString((configuration.getUsername() + ":" + configuration.getPassword()).getBytes());
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
        request.addHeader(HttpHeaders.USER_AGENT, configuration.getUserAgent());
        request.addHeader(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");

        try (CloseableHttpResponse response = httpClient.execute(request, getHttpContext())) {
            if (response.getStatusLine().getStatusCode() >= HttpStatus.SC_BAD_REQUEST) {
                throw throwError(response);
            }

            return readEntity(response);
        }
    }

    private <T extends HttpEntityEnclosingRequest> T setPayload(T request, Object dataObject) {
        try {
            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(dataObject));
            entity.setContentType("application/json;charset=UTF-8");
            request.setEntity(entity);
            return request;
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new MasternautException(e);
        }
    }

    private MasternautException throwError(CloseableHttpResponse response) {
        Header contentType = response.getFirstHeader(HttpHeaders.CONTENT_TYPE);
        if (contentType != null && contentType.getValue().startsWith("application/json")) {
            String content = null;
            try {
                content = readEntity(response);
                return new MasternautServerException(
                        response.getStatusLine().getStatusCode(),
                        response.getStatusLine().getReasonPhrase(),
                        objectMapper.readValue(content, MasternautError.class));
            } catch (IOException ignore) {
                return new MasternautServerException(
                        response.getStatusLine().getStatusCode(),
                        response.getStatusLine().getReasonPhrase(),
                        new MasternautError("400", content));
            }
        } else {
            return new MasternautServerException(
                    response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase(),
                    null);
        }
    }

    private MasternautException throwError(String content, IOException e) {
        try {
            return new MasternautServerException(
                    HttpStatus.SC_OK,
                    "OK",
                    objectMapper.readValue(content, MasternautError.class));
        } catch (IOException ignore) {
            return new MasternautException(e);
        }
    }

    private String readEntity(CloseableHttpResponse response) throws IOException {
        if (response.getEntity() == null) {
            return null;
        }
        return EntityUtils.toString(response.getEntity());
    }


    private CloseableHttpClient makeHttpClient(Configuration configuration) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(configuration.getMaxConnectionsPerRoute());
        connectionManager.setDefaultMaxPerRoute(configuration.getMaxConnectionsPerRoute());
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    private HttpClientContext getHttpContext() {
        HttpClientContext context = httpContext.get();
        if (context == null) {
            context = HttpClientContext.create();
            context.setRequestConfig(RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT_MILLIS)
                    .setConnectTimeout(TIMEOUT_MILLIS)
                    .setConnectionRequestTimeout(TIMEOUT_MILLIS)
                    .build());
            httpContext.set(context);
        }
        return context;
    }

    private URI getUri(String path, Object params) {
        StringBuilder uri = new StringBuilder(configuration.getEndpoint())
                .append(configuration.getEndpoint().endsWith("/") ? "" : "/")
                .append(configuration.getCustomerId())
                .append("/")
                .append(path);

        if (params != null) {
            uri.append(parameterMapper.write(params));
        }

        try {
            return new URI(uri.toString());
        } catch (URISyntaxException e) {
            throw new MasternautException(e);
        }
    }
}
