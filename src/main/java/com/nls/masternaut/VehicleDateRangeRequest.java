package com.nls.masternaut;

import org.joda.time.LocalDateTime;

public class VehicleDateRangeRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String vehicleId;
    private String groupId;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public VehicleDateRangeRequest withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public VehicleDateRangeRequest withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleDateRangeRequest withVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public VehicleDateRangeRequest withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
