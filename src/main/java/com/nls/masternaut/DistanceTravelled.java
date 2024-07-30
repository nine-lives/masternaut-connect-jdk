package com.nls.masternaut;

import java.math.BigDecimal;

public class DistanceTravelled {
    private String assetId;
    private String assetRegistration;
    private String assetName;
    private String groupName;
    private BigDecimal distance;
    private OdometerType odometerType;

    public String getAssetId() {
        return assetId;
    }

    public String getAssetRegistration() {
        return assetRegistration;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getGroupName() {
        return groupName;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public OdometerType getOdometerType() {
        return odometerType;
    }
}
