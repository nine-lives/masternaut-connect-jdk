package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

public class GroupAddRequest {
    @JsonIgnore
    private final transient IClient client;

    private String name;
    private String parentId;

    GroupAddRequest(IClient client) {
        this.client = client;
    }

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

    public Group commit() {
        return client.post("group", this, Group.class);
    }
}
