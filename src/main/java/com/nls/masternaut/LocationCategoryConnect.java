package com.nls.masternaut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.HttpClient;

import java.util.List;

public class LocationCategoryConnect {
    private final HttpClient client;

    LocationCategoryConnect(HttpClient client) {
        this.client = client;
    }

    public List<LocationCategory> list() {
        return client.get("location/category", null, new TypeReference<List<LocationCategory>>() { });
    }

    public LocationCategory get(String categoryId) {
        return client.get("location/category/" + categoryId, null, LocationCategory.class);
    }

    public LocationCategory add(LocationCategoryAddRequest request) {
        return client.post("location/category", null, LocationCategory.class);
    }

    public LocationCategory update(LocationCategoryUpdateRequest request) {
        return client.put("location/category", null, LocationCategory.class);
    }

    public void delete(String categoryId) {
        client.delete("location/category/" + categoryId, null, null);
    }
}
