package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDateTime;

public abstract class VehicleDateRangeRequest<T extends VehicleDateRangeRequest<T>> {
    @JsonIgnore
    private final transient Class<T> clazz;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String vehicleId;
    private String groupId;

    protected VehicleDateRangeRequest(Class<T> clazz) {
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

    public String getVehicleId() {
        return vehicleId;
    }

    public T withVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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
