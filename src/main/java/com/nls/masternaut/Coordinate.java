package com.nls.masternaut;

import java.math.BigDecimal;

public class Coordinate {
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Coordinate() {
    }

    public Coordinate(Coordinate copy) {
        this.longitude = copy.getLongitude();
        this.latitude = copy.getLatitude();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Coordinate withLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Coordinate withLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }
}
