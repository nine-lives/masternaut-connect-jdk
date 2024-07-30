package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.List;

public class LivePosition {
    private String assetId;
    private String assetRegistration;
    private String assetName;
    private String assetGroupId;
    private String assetGroupName;
    private VehicleType assetType;
    private String driverId;
    private String driverName;
    private String driverGroupId;
    private String driverGroupName;
    private LocalDateTime date;
    private LivePositionStatus status;
    private EventType eventType;
    private BigDecimal speed;
    private BigDecimal heading;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String roadNumber;
    private String road;
    private String city;

    @JsonAlias({"postcode", "postCode"})
    private String postcode;

    private String country;
    private String formattedAddress;
    private String locationName;
    private String locationGroupName;
    private String locationId;
    private List<Input> inputs;
    private List<InputEvent> inputEvents;
    private List<String> tags;
    private Boolean privacy;
    private BigDecimal odometer;
    private String energyType;
    private List<FuelType> fuelType;
    private BigDecimal electricBatteryLevelPercent;
    private Boolean electricBatteryLevelPercentEstimated;
    private BigDecimal electricRangeKm;
    private Boolean electricRangeKmEstimated;
    private Boolean electricCharging;
    private BigDecimal electricChargingTimeRemainingMins;
    private Integer engineRpm;
    private BigDecimal fuelLevelPercentage;
    private BigDecimal fuelLevelLitres;
    private BigDecimal engineTotalHours;
    private EngineTotalHoursType engineTotalHoursType;


    public String getAssetId() {
        return assetId;
    }

    public String getAssetRegistration() {
        return assetRegistration;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getAssetGroupId() {
        return assetGroupId;
    }

    public String getAssetGroupName() {
        return assetGroupName;
    }

    public VehicleType getAssetType() {
        return assetType;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverGroupId() {
        return driverGroupId;
    }

    public String getDriverGroupName() {
        return driverGroupName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LivePositionStatus getStatus() {
        return status;
    }

    public EventType getEventType() {
        return eventType;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public BigDecimal getHeading() {
        return heading;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String getRoadNumber() {
        return roadNumber;
    }

    public String getRoad() {
        return road;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationGroupName() {
        return locationGroupName;
    }

    public String getLocationId() {
        return locationId;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public List<InputEvent> getInputEvents() {
        return inputEvents;
    }

    public List<String> getTags() {
        return tags;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public BigDecimal getOdometer() {
        return odometer;
    }

    public String getEnergyType() {
        return energyType;
    }

    public List<FuelType> getFuelType() {
        return fuelType;
    }

    public BigDecimal getElectricBatteryLevelPercent() {
        return electricBatteryLevelPercent;
    }

    public Boolean getElectricBatteryLevelPercentEstimated() {
        return electricBatteryLevelPercentEstimated;
    }

    public BigDecimal getElectricRangeKm() {
        return electricRangeKm;
    }

    public Boolean getElectricRangeKmEstimated() {
        return electricRangeKmEstimated;
    }

    public Boolean getElectricCharging() {
        return electricCharging;
    }

    public BigDecimal getElectricChargingTimeRemainingMins() {
        return electricChargingTimeRemainingMins;
    }

    public Integer getEngineRpm() {
        return engineRpm;
    }

    public BigDecimal getFuelLevelPercentage() {
        return fuelLevelPercentage;
    }

    public BigDecimal getFuelLevelLitres() {
        return fuelLevelLitres;
    }

    public BigDecimal getEngineTotalHours() {
        return engineTotalHours;
    }

    public EngineTotalHoursType getEngineTotalHoursType() {
        return engineTotalHoursType;
    }
}
