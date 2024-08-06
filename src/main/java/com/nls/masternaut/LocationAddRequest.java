package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

public class LocationAddRequest extends LocationCommitRequest<LocationAddRequest> {
    @JsonIgnore
    private final transient IClient client;

    LocationAddRequest(IClient client) {
        super(LocationAddRequest.class);
        this.client = client;
    }

    public Location commit() {
        return client.post("location", this, Location.class);
    }
}
