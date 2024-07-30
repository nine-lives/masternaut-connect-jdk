package com.nls.masternaut;

import org.joda.time.LocalDateTime;

public class DriverDateRangeRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String driverId;
    private String groupId;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public DriverDateRangeRequest withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public DriverDateRangeRequest withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public DriverDateRangeRequest withDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public DriverDateRangeRequest withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
