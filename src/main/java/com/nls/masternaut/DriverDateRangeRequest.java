package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDateTime;

public abstract class DriverDateRangeRequest<T extends DriverDateRangeRequest<T>> {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String driverId;
    private String groupId;

    @JsonIgnore
    private final transient Class<T> clazz;

    DriverDateRangeRequest(Class<T> clazz) {
        this.clazz = clazz;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public T withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return clazz.cast(this);
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public T withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return clazz.cast(this);
    }

    public String getDriverId() {
        return driverId;
    }

    public T withDriverId(String driverId) {
        this.driverId = driverId;
        return clazz.cast(this);
    }

    public String getGroupId() {
        return groupId;
    }

    public T withGroupId(String groupId) {
        this.groupId = groupId;
        return clazz.cast(this);
    }
}
