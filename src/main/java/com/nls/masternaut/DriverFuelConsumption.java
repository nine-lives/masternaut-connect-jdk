package com.nls.masternaut;

import java.util.List;

public class DriverFuelConsumption {
    private String driverId;
    private String driverName;
    private List<VehicleFuelConsumption> vehicles;

    public String getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public List<VehicleFuelConsumption> getVehicles() {
        return vehicles;
    }
}
