package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

public class LocationCategoryAddRequest {
    @JsonIgnore
    private final transient IClient client;

    private String name;
    private String icon;

    LocationCategoryAddRequest(IClient client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public LocationCategoryAddRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public LocationCategoryAddRequest withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public LocationCategory commit() {
        return client.post("location/category", null, LocationCategory.class);
    }
}
