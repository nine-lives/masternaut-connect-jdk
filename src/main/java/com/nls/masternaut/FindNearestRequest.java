package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;

public abstract class FindNearestRequest<T extends FindNearestRequest<T>> {
    private Integer radius;
    private Integer maximumResultsToReturn;
    private String roadNumber;
    private String road;
    private String city;
    private String postCode;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime lastMovedStartDateTime;
    private LocalDateTime lastMovedEndDateTime;

    @JsonIgnore
    private final transient Class<T> clazz;

    protected FindNearestRequest(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Integer getRadius() {
        return radius;
    }

    public T withRadius(Integer radius) {
        this.radius = radius;
        return clazz.cast(this);
    }

    public Integer getMaximumResultsToReturn() {
        return maximumResultsToReturn;
    }

    public T withMaximumResultsToReturn(Integer maximumResultsToReturn) {
        this.maximumResultsToReturn = maximumResultsToReturn;
        return clazz.cast(this);
    }

    public String getRoadNumber() {
        return roadNumber;
    }

    public T withRoadNumber(String roadNumber) {
        this.roadNumber = roadNumber;
        return clazz.cast(this);
    }

    public String getRoad() {
        return road;
    }

    public T withRoad(String road) {
        this.road = road;
        return clazz.cast(this);
    }

    public String getCity() {
        return city;
    }

    public T withCity(String city) {
        this.city = city;
        return clazz.cast(this);
    }

    public String getPostCode() {
        return postCode;
    }

    public T withPostCode(String postCode) {
        this.postCode = postCode;
        return clazz.cast(this);
    }

    public String getCountry() {
        return country;
    }

    public T withCountry(String country) {
        this.country = country;
        return clazz.cast(this);
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public T withLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return clazz.cast(this);
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public T withLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return clazz.cast(this);
    }

    public LocalDateTime getLastMovedStartDateTime() {
        return lastMovedStartDateTime;
    }

    public T withLastMovedStartDateTime(LocalDateTime lastMovedStartDateTime) {
        this.lastMovedStartDateTime = lastMovedStartDateTime;
        return clazz.cast(this);
    }

    public LocalDateTime getLastMovedEndDateTime() {
        return lastMovedEndDateTime;
    }

    public T withLastMovedEndDateTime(LocalDateTime lastMovedEndDateTime) {
        this.lastMovedEndDateTime = lastMovedEndDateTime;
        return clazz.cast(this);
    }
}
