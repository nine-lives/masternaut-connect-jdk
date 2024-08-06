package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;
import org.joda.time.LocalDateTime;

import java.util.List;

public class LatestLivePositionRequest {
    @JsonIgnore
    private final transient IClient client;

    private LocalDateTime fromDateTime;
    private List<String> vehicleIds;
    private List<String> driverIds;
    private List<String> groupIds;
    private Boolean showAllVehicleStatus;
    private Boolean hideSpeedIfSpeedSensitive;

    LatestLivePositionRequest(IClient client) {
        this.client = client;
    }

    LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LatestLivePositionRequest withFromDateTime(LocalDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
        return this;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public LatestLivePositionRequest withVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return this;
    }

    public List<String> getDriverIds() {
        return driverIds;
    }

    public LatestLivePositionRequest withDriverIds(List<String> driverIds) {
        this.driverIds = driverIds;
        return this;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public LatestLivePositionRequest withGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public Boolean getShowAllVehicleStatus() {
        return showAllVehicleStatus;
    }

    public LatestLivePositionRequest withShowAllVehicleStatus(Boolean showAllVehicleStatus) {
        this.showAllVehicleStatus = showAllVehicleStatus;
        return this;
    }

    public Boolean getHideSpeedIfSpeedSensitive() {
        return hideSpeedIfSpeedSensitive;
    }

    public LatestLivePositionRequest withHideSpeedIfSpeedSensitive(Boolean hideSpeedIfSpeedSensitive) {
        this.hideSpeedIfSpeedSensitive = hideSpeedIfSpeedSensitive;
        return this;
    }

    private LatestLivePositionRequest copy() {
        return new LatestLivePositionRequest(client)
                .withDriverIds(driverIds)
                .withGroupIds(groupIds)
                .withVehicleIds(vehicleIds)
                .withHideSpeedIfSpeedSensitive(hideSpeedIfSpeedSensitive)
                .withShowAllVehicleStatus(showAllVehicleStatus);
    }

    public LatestLivePositionList fetch() {
        LatestLivePositionRequest clone = copy();
        return client.get("tracking/live/latest", withFromDateTime(fromDateTime), LatestLivePositionList.class)
                .withRefresh((from) -> clone.withFromDateTime(from).fetch());
    }
}
