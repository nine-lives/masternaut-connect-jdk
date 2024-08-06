package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class GroupListRequest {
    @JsonIgnore
    private final transient IClient client;

    GroupListRequest(IClient client) {
        this.client = client;
    }

    public List<Group> fetch() {
        return client.get("group", null, new TypeReference<List<Group>>() { });
    }
}
