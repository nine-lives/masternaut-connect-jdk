package com.nls.masternaut;

import com.nls.masternaut.client.IClient;

import java.util.List;

public class VehicleListRequest extends PageRequest<VehicleListRequest, Vehicle> {

    private List<String> vehicleIds;
    private List<String> groupIds;
    private String name;

    VehicleListRequest(IClient client) {
        super(client, VehicleListRequest.class);
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

    @Override
    public int getMaxPageSize() {
        return 200;
    }

    @Override
    protected String getPath() {
        return "vehicle";
    }
}
