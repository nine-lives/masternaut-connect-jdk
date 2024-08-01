package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.joda.time.LocalDateTime;

import java.util.List;

public class Driver {
    private String id;
    private String customerId;
    @JsonAlias({"name",  "driverName"})
    private String name;
    private String groupId;
    private String groupName;
    private Boolean active;
    private LocalDateTime activeDate;
    private String timezoneId;
    private String defaultVehicleId;
    private List<DriverKey> keys;
    private List<String> tags;
    private String tempVehicleId;
    private LocalDateTime tempVehicleExpiry;
    private Boolean tempVehicleNextJourneyOnly;
    private Boolean tempPrivateMode;
    private Boolean tempPrivateModeNextJourneyOnly;
    private Boolean autoCreated;

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public String getDefaultVehicleId() {
        return defaultVehicleId;
    }

    public List<DriverKey> getKeys() {
        return keys;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTempVehicleId() {
        return tempVehicleId;
    }

    public LocalDateTime getTempVehicleExpiry() {
        return tempVehicleExpiry;
    }

    public Boolean getTempVehicleNextJourneyOnly() {
        return tempVehicleNextJourneyOnly;
    }

    public Boolean getTempPrivateMode() {
        return tempPrivateMode;
    }

    public Boolean getTempPrivateModeNextJourneyOnly() {
        return tempPrivateModeNextJourneyOnly;
    }

    public Boolean getAutoCreated() {
        return autoCreated;
    }
}
