package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class DriverCommitRequest<T extends DriverCommitRequest<T>> {
    private String id;
    private String name;
    private String groupId;
    private List<String> tags;
    private Boolean active;
    private List<DriverKey> keys;

    @JsonIgnore
    private final transient Class<T> clazz;

    protected DriverCommitRequest(Class<T> clazz) {
        this.clazz = clazz;
    }

    String getId() {
        return id;
    }

    T withId(String id) {
        this.id = id;
        return clazz.cast(this);
    }

    public String getName() {
        return name;
    }

    public T withName(String name) {
        this.name = name;
        return clazz.cast(this);
    }

    public String getGroupId() {
        return groupId;
    }

    public T withGroupId(String groupId) {
        this.groupId = groupId;
        return clazz.cast(this);
    }

    public List<String> getTags() {
        return tags;
    }

    public T withTags(List<String> tags) {
        this.tags = tags;
        return clazz.cast(this);
    }

    public Boolean getActive() {
        return active;
    }

    public T withActive(Boolean active) {
        this.active = active;
        return clazz.cast(this);
    }

    public List<DriverKey> getKeys() {
        return keys;
    }

    public T withKeys(List<DriverKey> keys) {
        this.keys = keys;
        return clazz.cast(this);
    }
}
