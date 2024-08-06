package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

import java.util.ArrayList;
import java.util.List;

public class VehicleUpdateRequest {
    @JsonIgnore
    private final transient IClient client;

    @JsonIgnore
    private final transient String id;

    private String groupId;
    private String name;
    private List<String> tags;
    private VehicleType type;
    private VehicleStatus status;
    private AssetCosts assetCosts;

    public VehicleUpdateRequest(IClient client, String vehicleId) {
        this.client = client;
        this.id = vehicleId;
    }

    public VehicleUpdateRequest withVehicle(Vehicle vehicle) {
        this.groupId = vehicle.getGroupId();
        this.name = vehicle.getName();
        this.tags = vehicle.getTags() == null ? null : new ArrayList<String>(vehicle.getTags());
        this.type = vehicle.getType();
        this.status = vehicle.getStatus();
        this.assetCosts = vehicle.getAssetCosts() == null ? null : new AssetCosts(vehicle.getAssetCosts());
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public VehicleUpdateRequest withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public VehicleUpdateRequest withName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public VehicleUpdateRequest withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleUpdateRequest withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public VehicleUpdateRequest withStatus(VehicleStatus status) {
        this.status = status;
        return this;
    }

    public AssetCosts getAssetCosts() {
        return assetCosts;
    }

    public VehicleUpdateRequest withAssetCosts(AssetCosts assetCosts) {
        this.assetCosts = assetCosts;
        return this;
    }

    public Vehicle commit() {
        return client.put("vehicle/" + id, this, Vehicle.class);
    }
}
