package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDateTime;

import java.util.List;

public abstract class VehicleListDateRangeRequest<T extends VehicleListDateRangeRequest<T>> {
    @JsonIgnore
    private final transient Class<T> clazz;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> vehicleIds;
    private List<String> groupIds;

    protected VehicleListDateRangeRequest(Class<T> clazz) {
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

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public T withVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return clazz.cast(this);
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public T withGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return clazz.cast(this);
    }
}
