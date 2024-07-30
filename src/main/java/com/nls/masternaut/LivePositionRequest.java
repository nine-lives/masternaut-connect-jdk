package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.util.List;

public class LivePositionRequest {

    private LocalDateTime fromDateTime;
    private List<String> vehicleIds;
    private List<String> driverIds;
    private List<String> groupIds;
    private Boolean showAllVehicleStatus;
    private Boolean hideSpeedIfSpeedSensitive;

    LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    LivePositionRequest withFromDateTime(LocalDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
        return this;
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
        return new LivePositionRequest()
                .withDriverIds(driverIds)
                .withGroupIds(groupIds)
                .withVehicleIds(vehicleIds)
                .withHideSpeedIfSpeedSensitive(hideSpeedIfSpeedSensitive)
                .withShowAllVehicleStatus(showAllVehicleStatus);
    }
}
