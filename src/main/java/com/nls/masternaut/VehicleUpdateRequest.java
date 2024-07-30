package com.nls.masternaut;

import java.util.ArrayList;
import java.util.List;

public class VehicleUpdateRequest {
    private String groupId;
    private String name;
    private List<String> tags;
    private VehicleType type;
    private VehicleStatus status;
    private AssetCosts assetCosts;

    public VehicleUpdateRequest() {
    }

    public VehicleUpdateRequest(Vehicle vehicle) {
        this.groupId = vehicle.getGroupId();
        this.name = vehicle.getName();
        this.tags = vehicle.getTags() == null ? null : new ArrayList<String>(vehicle.getTags());
        this.type = vehicle.getType();
        this.status = vehicle.getStatus();
        this.assetCosts = vehicle.getAssetCosts() == null ? null : new AssetCosts(vehicle.getAssetCosts());
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
}
