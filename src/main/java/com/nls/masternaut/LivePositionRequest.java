package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class LivePositionRequest {
    @JsonIgnore
    private final transient IClient client;

    private List<String> vehicleIds;
    private List<String> driverIds;
    private List<String> groupIds;
    private Boolean showAllVehicleStatus;
    private Boolean hideSpeedIfSpeedSensitive;

    LivePositionRequest(IClient client) {
        this.client = client;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public LivePositionRequest withVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return this;
    }

    public List<String> getDriverIds() {
        return driverIds;
    }

    public LivePositionRequest withDriverIds(List<String> driverIds) {
        this.driverIds = driverIds;
        return this;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public LivePositionRequest withGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public Boolean getShowAllVehicleStatus() {
        return showAllVehicleStatus;
    }

    public LivePositionRequest withShowAllVehicleStatus(Boolean showAllVehicleStatus) {
        this.showAllVehicleStatus = showAllVehicleStatus;
        return this;
    }

    public Boolean getHideSpeedIfSpeedSensitive() {
        return hideSpeedIfSpeedSensitive;
    }

    public LivePositionRequest withHideSpeedIfSpeedSensitive(Boolean hideSpeedIfSpeedSensitive) {
        this.hideSpeedIfSpeedSensitive = hideSpeedIfSpeedSensitive;
        return this;
    }

    public LivePositionRequest copy() {
        return new LivePositionRequest(client)
                .withDriverIds(driverIds)
                .withGroupIds(groupIds)
                .withVehicleIds(vehicleIds)
                .withHideSpeedIfSpeedSensitive(hideSpeedIfSpeedSensitive)
                .withShowAllVehicleStatus(showAllVehicleStatus);
    }

    public List<LivePosition> fetch() {
        return client.get("tracking/live", this, new TypeReference<List<LivePosition>>() { });
    }
}
