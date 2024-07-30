package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;

public class FindNearestRequest {
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

    public Integer getRadius() {
        return radius;
    }

    public FindNearestRequest withRadius(Integer radius) {
        this.radius = radius;
        return this;
    }

    public Integer getMaximumResultsToReturn() {
        return maximumResultsToReturn;
    }

    public FindNearestRequest withMaximumResultsToReturn(Integer maximumResultsToReturn) {
        this.maximumResultsToReturn = maximumResultsToReturn;
        return this;
    }

    public String getRoadNumber() {
        return roadNumber;
    }

    public FindNearestRequest withRoadNumber(String roadNumber) {
        this.roadNumber = roadNumber;
        return this;
    }

    public String getRoad() {
        return road;
    }

    public FindNearestRequest withRoad(String road) {
        this.road = road;
        return this;
    }

    public String getCity() {
        return city;
    }

    public FindNearestRequest withCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public FindNearestRequest withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public FindNearestRequest withCountry(String country) {
        this.country = country;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public FindNearestRequest withLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public FindNearestRequest withLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocalDateTime getLastMovedStartDateTime() {
        return lastMovedStartDateTime;
    }

    public FindNearestRequest withLastMovedStartDateTime(LocalDateTime lastMovedStartDateTime) {
        this.lastMovedStartDateTime = lastMovedStartDateTime;
        return this;
    }

    public LocalDateTime getLastMovedEndDateTime() {
        return lastMovedEndDateTime;
    }

    public FindNearestRequest withLastMovedEndDateTime(LocalDateTime lastMovedEndDateTime) {
        this.lastMovedEndDateTime = lastMovedEndDateTime;
        return this;
    }
}
