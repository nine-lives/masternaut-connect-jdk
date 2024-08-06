package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class VehicleFindNearestRequest extends FindNearestRequest<VehicleFindNearestRequest> {
    @JsonIgnore
    private final transient IClient client;

    VehicleFindNearestRequest(IClient client) {
        super(VehicleFindNearestRequest.class);
        this.client = client;
    }

    public List<ObjectDistance> fetch() {
        return client.get("vehicle/nearest", this, new TypeReference<List<ObjectDistance>>() { });
    }
}
