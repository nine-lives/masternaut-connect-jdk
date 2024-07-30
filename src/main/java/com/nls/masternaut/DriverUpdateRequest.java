package com.nls.masternaut;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DriverUpdateRequest {
    private String id;
    private String name;
    private String groupId;
    private List<String> tags;
    private Boolean active;
    private List<DriverKey> keys;

    public DriverUpdateRequest() {

    }

    public DriverUpdateRequest(Driver copy) {
        this.id = copy.getId();
        this.name = copy.getName();
        this.groupId = copy.getGroupId();
        this.tags = copy.getTags() == null ? null : new ArrayList<String>(copy.getTags());
        this.active = copy.getActive();
        this.keys = copy.getKeys() == null ? null : keys.stream().map(DriverKey::new).collect(Collectors.toList());
    }

    String getId() {
        return id;
    }

    DriverUpdateRequest withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DriverUpdateRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public DriverUpdateRequest withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public DriverUpdateRequest withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public DriverUpdateRequest withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public List<DriverKey> getKeys() {
        return keys;
    }

    public DriverUpdateRequest withKeys(List<DriverKey> keys) {
        this.keys = keys;
        return this;
    }
}
