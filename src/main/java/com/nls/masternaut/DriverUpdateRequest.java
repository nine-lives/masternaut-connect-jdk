package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DriverUpdateRequest extends DriverCommitRequest<DriverUpdateRequest> {
    @JsonIgnore
    private final transient IClient client;

    private String id;

    public DriverUpdateRequest(IClient client) {
        super(DriverUpdateRequest.class);
        this.client = client;
    }

    public DriverUpdateRequest withDriver(Driver copy) {
        this.id = copy.getId();
        return withName(copy.getName())
                .withGroupId(copy.getGroupId())
                .withTags(copy.getTags() == null ? null : new ArrayList<>(copy.getTags()))
                .withActive(copy.getActive())
                .withKeys(copy.getKeys() == null ? null : copy.getKeys().stream().map(DriverKey::new).collect(Collectors.toList()));
    }

    String getId() {
        return id;
    }

    DriverUpdateRequest withId(String id) {
        this.id = id;
        return this;
    }

    public Driver commit() {
        return client.put("driver/" + id, this, Driver.class);
    }
}
