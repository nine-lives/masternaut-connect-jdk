package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class VehicleFuelConsumptionRequest extends VehicleDateRangeRequest<VehicleFuelConsumptionRequest> {
    @JsonIgnore
    private final transient IClient client;

    VehicleFuelConsumptionRequest(IClient client) {
        super(VehicleFuelConsumptionRequest.class);
        this.client = client;
    }

    public List<DriverFuelConsumption> fetch() {
        return client.get("fuel/driver", this, new TypeReference<List<DriverFuelConsumption>>() { });
    }
}
