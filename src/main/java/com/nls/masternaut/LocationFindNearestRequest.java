package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class LocationFindNearestRequest extends FindNearestRequest<LocationFindNearestRequest> {
    @JsonIgnore
    private final transient IClient client;

    LocationFindNearestRequest(IClient client) {
        super(LocationFindNearestRequest.class);
        this.client = client;
    }

    public List<ObjectDistance> fetch() {
        return client.get("location/nearest", this, new TypeReference<List<ObjectDistance>>() { });
    }
}
