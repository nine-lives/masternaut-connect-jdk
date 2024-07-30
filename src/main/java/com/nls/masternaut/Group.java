package com.nls.masternaut;

import java.util.List;

public class Group {
    private String id;
    private String name;
    private String parentId;
    private String path;
    private Integer countVehicles;
    private List<String> vehicleIds;
    private Integer countDrivers;
    private List<String> personIds;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public String getPath() {
        return path;
    }

    public Integer getCountVehicles() {
        return countVehicles == null && vehicleIds != null ? (Integer) vehicleIds.size() : countVehicles;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public Integer getCountDrivers() {
        return countDrivers == null && personIds != null ? (Integer) personIds.size() : countDrivers;
    }

    public List<String> getPersonIds() {
        return personIds;
    }
}
