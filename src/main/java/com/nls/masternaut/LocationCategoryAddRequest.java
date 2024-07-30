package com.nls.masternaut;

public class LocationCategoryAddRequest {
    private String name;
    private String icon;

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
}
