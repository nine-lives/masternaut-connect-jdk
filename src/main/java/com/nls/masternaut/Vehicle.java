package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.List;

public class Vehicle {
    private String id;
    private String registration;
    private String name;
    private VehicleType type;
    private String assetTypeGroup;
    private String groupId;
    private String groupName;
    private BigDecimal odometerValue;
    private OdometerType odometerType;
    private LocalDateTime odometerLastModified;
    private String make;
    private String model;
    private List<String> tags;
    private List<String> featureTags;
    private String defaultDriverId;
    private VehicleStatus status;
    private AssetCosts assetCosts;
    private Integer idlingFidelity;
    private String energyType;
    private List<FuelType> fuelType;
    private BigDecimal engineTotalHours;
    private EngineTotalHoursType engineTotalHoursType;
    private LocalDateTime engineTotalHoursLastModified;

    public String getId() {
        return id;
    }

    public String getRegistration() {
        return registration;
    }

    public String getName() {
        return name;
    }

    public VehicleType getType() {
        return type;
    }

    public String getAssetTypeGroup() {
        return assetTypeGroup;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public BigDecimal getOdometerValue() {
        return odometerValue;
    }

    public OdometerType getOdometerType() {
        return odometerType;
    }

    public LocalDateTime getOdometerLastModified() {
        return odometerLastModified;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getFeatureTags() {
        return featureTags;
    }

    public String getDefaultDriverId() {
        return defaultDriverId;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public AssetCosts getAssetCosts() {
        return assetCosts;
    }

    public Integer getIdlingFidelity() {
        return idlingFidelity;
    }

    public String getEnergyType() {
        return energyType;
    }

    public List<FuelType> getFuelType() {
        return fuelType;
    }

    public BigDecimal getEngineTotalHours() {
        return engineTotalHours;
    }

    public EngineTotalHoursType getEngineTotalHoursType() {
        return engineTotalHoursType;
    }

    public LocalDateTime getEngineTotalHoursLastModified() {
        return engineTotalHoursLastModified;
    }
}
