package com.nls.masternaut;

public class LocationCategoryUpdateRequest {
    private String id;
    private String name;
    private String icon;

    protected LocationCategoryUpdateRequest() {
    }

    public LocationCategoryUpdateRequest(String id) {
        this.id = id;
    }

    public LocationCategoryUpdateRequest(LocationCategory category) {
        this.id = category.getId();
        this.name = category.getName();
        this.icon = category.getIcon();
    }

    public String getId() {
        return id;
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
}
