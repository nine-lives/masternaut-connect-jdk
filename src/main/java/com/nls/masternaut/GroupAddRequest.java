package com.nls.masternaut;

public class GroupAddRequest {
    private String name;
    private String parentId;

    public String getName() {
        return name;
    }

    public GroupAddRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public GroupAddRequest withParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }
}
