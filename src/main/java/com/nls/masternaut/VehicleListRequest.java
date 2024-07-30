package com.nls.masternaut;

import java.util.List;

public class VehicleListRequest extends PageRequest<VehicleListRequest> {

    private List<String> vehicleIds;
    private List<String> groupIds;
    private String name;

    public VehicleListRequest() {
        super(VehicleListRequest.class);
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public VehicleListRequest withVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return this;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public VehicleListRequest withGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public String getName() {
        return name;
    }

    public VehicleListRequest withName(String name) {
        this.name = name;
        return this;
    }
}
