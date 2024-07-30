package com.nls.masternaut;

import java.util.List;

public class GroupUpdateRequest {
    private String id;
    private String name;
    private String parentId;
    private List<String> vehicleIds;
    private List<String> personIds;

    protected  GroupUpdateRequest() {

    }

    public GroupUpdateRequest(String id) {
        this.id = id;
    }

    public GroupUpdateRequest(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.parentId = group.getParentId();
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
}
