package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class LocationCategoryListRequest {
    @JsonIgnore
    private final transient IClient client;

    LocationCategoryListRequest(IClient client) {
        this.client = client;
    }

    public List<LocationCategory> fetch() {
        return client.get("location/category", null, new TypeReference<List<LocationCategory>>() { });
    }
}
