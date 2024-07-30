package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.util.List;

public class VehicleListDateRangeRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> vehicleIds;
    private List<String> groupIds;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public VehicleListDateRangeRequest withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public VehicleListDateRangeRequest withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public VehicleListDateRangeRequest withVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return this;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public VehicleListDateRangeRequest withGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return this;
    }
}
