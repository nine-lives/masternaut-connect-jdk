package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class GroupUpdateRequest {
    @JsonIgnore
    private final transient IClient client;

    private final String id;
    private String name;
    private String parentId;
    private List<String> vehicleIds;
    private List<String> personIds;

    GroupUpdateRequest(IClient client, String groupId) {
        this.client = client;
        this.id = groupId;
    }

    public GroupUpdateRequest withGroup(Group group) {
        this.name = group.getName();
        this.parentId = group.getParentId();
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GroupUpdateRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public GroupUpdateRequest withParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public GroupUpdateRequest withVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return this;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public GroupUpdateRequest withPersonIds(List<String> personIds) {
        this.personIds = personIds;
        return this;
    }

    public Group commit() {
        return client.put("group", this, Group.class);
    }
}
