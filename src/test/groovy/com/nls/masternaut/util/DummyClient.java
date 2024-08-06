package com.nls.masternaut.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nls.masternaut.MasternautException;
import com.nls.masternaut.client.IClient;

public class DummyClient implements IClient {
    private final RequestParameterMapper parameterMapper = new RequestParameterMapper();
    private final ObjectMapper objectMapper = ObjectMapperFactory.make();

    private String nextResponse;
    private String lastPath;
    private String lastParameters;
    private String lastRequestBody;
    private String lastMethod;

    public void setNextResponse(String nextResponse) {
        this.nextResponse = nextResponse;
    }

    public String getLastPath() {
        return lastPath;
    }

    public String getLastParameters() {
        return lastParameters;
    }

    public String getLastRequestBody() {
        return lastRequestBody;
    }

    public String getLastMethod() {
        return lastMethod;
    }

    @Override
    public <T> T get(String path, Object parameters, TypeReference<T> responseType) {
        lastMethod = "GET";
        getUri(path, parameters);
        lastRequestBody = null;
        try {
            return nextResponse == null ? null : objectMapper.readValue(nextResponse, responseType);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
    }

    @Override
    public <T> T get(String path, Object parameters, Class<T> responseType) {
        lastMethod = "GET";
        getUri(path, parameters);
        lastRequestBody = null;
        try {
            return nextResponse == null ? null : objectMapper.readValue(nextResponse, responseType);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
    }

    @Override
    public <T> T post(String path, Object data, Class<T> responseType) {
        lastMethod = "POST";
        getUri(path, null);
        try {
            lastRequestBody = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
        try {
            return nextResponse == null ? null : objectMapper.readValue(nextResponse, responseType);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
    }

    @Override
    public <T> T put(String path, Object data, Class<T> responseType) {
        lastMethod = "PUT";
        getUri(path, null);
        try {
            lastRequestBody = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
        try {
            return nextResponse == null ? null : objectMapper.readValue(nextResponse, responseType);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
    }

    @Override
    public <T> T delete(String path, Object parameters, Class<T> responseType) {
        lastMethod = "DELETE";
        getUri(path, parameters);
        lastRequestBody = null;
        try {
            return nextResponse == null ? null : objectMapper.readValue(nextResponse, responseType);
        } catch (Exception e) {
            throw new MasternautException(e);
        }
    }

    private void getUri(String path, Object params) {
        lastPath = path;
        lastParameters = params == null ? null : parameterMapper.write(params);
    }
}
