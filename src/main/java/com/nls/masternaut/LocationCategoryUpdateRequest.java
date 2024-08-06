package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

public class LocationCategoryUpdateRequest {
    @JsonIgnore
    private final transient IClient client;

    private String id;
    private String name;
    private String icon;

    public LocationCategoryUpdateRequest(IClient client, String categoryId) {
        this.client = client;
        this.id = categoryId;
    }

    public String getId() {
        return id;
    }

    public LocationCategoryUpdateRequest withLocationCategory(LocationCategory category) {
        this.id = category.getId();
        this.name = category.getName();
        this.icon = category.getIcon();
        return this;
    }

    public String getName() {
        return name;
    }

    public LocationCategoryUpdateRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public LocationCategoryUpdateRequest withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public LocationCategory commit() {
        return client.put("location/category", null, LocationCategory.class);
    }
}
