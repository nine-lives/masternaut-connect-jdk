package com.nls.masternaut.client;

import com.fasterxml.jackson.core.type.TypeReference;

public interface IClient {
    <T> T get(String path, Object parameters, TypeReference<T> responseType);

    <T> T get(String path, Object parameters, Class<T> responseType);

    <T> T post(String path, Object data, Class<T> responseType);

    <T> T put(String path, Object data, Class<T> responseType);

    <T> T delete(String path, Object parameters, Class<T> responseType);
}
