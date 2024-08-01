package com.nls.masternaut;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

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

    public BigDecimal getDistance() {
        return sum(VehicleFuelConsumption::getDistance);
    }

    public BigDecimal getElectricUsedkWh() {
        return sum(VehicleFuelConsumption::getElectricUsedkWh);
    }

    public BigDecimal getGasUsedKg() {
        return sum(VehicleFuelConsumption::getGasUsedKg);
    }

    public BigDecimal getFuelUsedLitres() {
        return sum(VehicleFuelConsumption::getFuelUsedLitres);
    }

    public BigDecimal getFuelUsedPetrolEquivalentLitres() {
        return sum(VehicleFuelConsumption::getFuelUsedPetrolEquivalentLitres);
    }

    public BigDecimal getCo2() {
        return sum(VehicleFuelConsumption::getCo2);
    }

    public BigDecimal getTotalFuel() {
        return sum(VehicleFuelConsumption::getTotalFuel);
    }

    private BigDecimal sum(Function<VehicleFuelConsumption, BigDecimal> mapper) {
        return vehicles.stream()
                .map(mapper)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
