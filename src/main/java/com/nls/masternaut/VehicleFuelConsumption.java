package com.nls.masternaut;

import java.math.BigDecimal;
import java.util.List;

public class VehicleFuelConsumption {
    private String assetId;
    private String assetRegistration;
    private String assetName;
    private String energyType;
    private List<FuelType> fuelType;
    private BigDecimal distance;
    private BigDecimal electricUsedkWh;
    private BigDecimal gasUsedKg;
    private BigDecimal fuelUsedLitres;
    private BigDecimal fuelUsedPetrolEquivalentLitres;
    private BigDecimal co2;
    private BigDecimal totalFuel;
    private BigDecimal benchmarkConsumption;
    private BigDecimal manufacturerConsumption;

    public String getAssetId() {
        return assetId;
    }

    public String getAssetRegistration() {
        return assetRegistration;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getEnergyType() {
        return energyType;
    }

    public List<FuelType> getFuelType() {
        return fuelType;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public BigDecimal getElectricUsedkWh() {
        return electricUsedkWh;
    }

    public BigDecimal getGasUsedKg() {
        return gasUsedKg;
    }

    public BigDecimal getFuelUsedLitres() {
        return fuelUsedLitres;
    }

    public BigDecimal getFuelUsedPetrolEquivalentLitres() {
        return fuelUsedPetrolEquivalentLitres;
    }

    public BigDecimal getCo2() {
        return co2;
    }

    public BigDecimal getTotalFuel() {
        return totalFuel;
    }

    public BigDecimal getBenchmarkConsumption() {
        return benchmarkConsumption;
    }

    public BigDecimal getManufacturerConsumption() {
        return manufacturerConsumption;
    }
}
