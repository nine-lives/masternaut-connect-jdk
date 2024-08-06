package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

public class LocationCategoryConnect {
    private final HttpClient client;

    LocationCategoryConnect(HttpClient client) {
        this.client = client;
    }

    public LocationCategoryListRequest list() {
        return new LocationCategoryListRequest(client);
    }

    public LocationCategory get(String categoryId) {
        return client.get("location/category/" + categoryId, null, LocationCategory.class);
    }

    public LocationCategoryAddRequest add() {
        return new LocationCategoryAddRequest(client);
    }

    public LocationCategoryUpdateRequest update(String categoryId) {
        return new LocationCategoryUpdateRequest(client, categoryId);
    }

    public void delete(String categoryId) {
        client.delete("location/category/" + categoryId, null, null);
    }
}
