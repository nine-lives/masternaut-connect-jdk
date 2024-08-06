package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class DriverFuelConsumptionRequest extends DriverDateRangeRequest<DriverFuelConsumptionRequest> {
    @JsonIgnore
    private final transient IClient client;

    DriverFuelConsumptionRequest(IClient client) {
        super(DriverFuelConsumptionRequest.class);
        this.client = client;
    }

    public List<VehicleFuelConsumption> fetch() {
        return client.get("fuel/vehicle", this, new TypeReference<List<VehicleFuelConsumption>>() { });
    }
}
