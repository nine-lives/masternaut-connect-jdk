package com.nls.masternaut;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class AssetCosts {
    private Integer yearOfManufacture;
    private LocalDate ownershipStartDate;
    private LocalDate ownershipEndDate;
    private BigDecimal purchaseCost;
    private BigDecimal monthlyLeaseCost;
    private BigDecimal monthlyInsuranceCost;
    private BigDecimal monthlyMaintenanceCost;
    private BigDecimal scheduledMaintenanceAmount;
    private BigDecimal unscheduledMaintenanceAmount;
    private BigDecimal monthlyFuelCost;
    private Integer mileageAuthorised;
    private BigDecimal costPerMileOverLimit;
    private BigDecimal costOfDisposal;
    private BigDecimal buyoutAmount;
    private String costCurrency;

    public AssetCosts() {
    }

    public AssetCosts(AssetCosts copy) {
        this.yearOfManufacture = copy.yearOfManufacture;
        this.ownershipStartDate = copy.ownershipStartDate;
        this.ownershipEndDate = copy.ownershipEndDate;
        this.purchaseCost = copy.purchaseCost;
        this.monthlyLeaseCost = copy.monthlyLeaseCost;
        this.monthlyInsuranceCost = copy.monthlyInsuranceCost;
        this.monthlyMaintenanceCost = copy.monthlyMaintenanceCost;
        this.scheduledMaintenanceAmount = copy.scheduledMaintenanceAmount;
        this.unscheduledMaintenanceAmount = copy.unscheduledMaintenanceAmount;
        this.monthlyFuelCost = copy.monthlyFuelCost;
        this.mileageAuthorised = copy.mileageAuthorised;
        this.costPerMileOverLimit = copy.costPerMileOverLimit;
        this.costOfDisposal = copy.costOfDisposal;
        this.buyoutAmount = copy.buyoutAmount;
        this.costCurrency = copy.costCurrency;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public AssetCosts withYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public LocalDate getOwnershipStartDate() {
        return ownershipStartDate;
    }

    public AssetCosts withOwnershipStartDate(LocalDate ownershipStartDate) {
        this.ownershipStartDate = ownershipStartDate;
        return this;
    }

    public LocalDate getOwnershipEndDate() {
        return ownershipEndDate;
    }

    public AssetCosts withOwnershipEndDate(LocalDate ownershipEndDate) {
        this.ownershipEndDate = ownershipEndDate;
        return this;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public AssetCosts withPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
        return this;
    }

    public BigDecimal getMonthlyLeaseCost() {
        return monthlyLeaseCost;
    }

    public AssetCosts withMonthlyLeaseCost(BigDecimal monthlyLeaseCost) {
        this.monthlyLeaseCost = monthlyLeaseCost;
        return this;
    }

    public BigDecimal getMonthlyInsuranceCost() {
        return monthlyInsuranceCost;
    }

    public AssetCosts withMonthlyInsuranceCost(BigDecimal monthlyInsuranceCost) {
        this.monthlyInsuranceCost = monthlyInsuranceCost;
        return this;
    }

    public BigDecimal getMonthlyMaintenanceCost() {
        return monthlyMaintenanceCost;
    }

    public AssetCosts withMonthlyMaintenanceCost(BigDecimal monthlyMaintenanceCost) {
        this.monthlyMaintenanceCost = monthlyMaintenanceCost;
        return this;
    }

    public BigDecimal getScheduledMaintenanceAmount() {
        return scheduledMaintenanceAmount;
    }

    public AssetCosts withScheduledMaintenanceAmount(BigDecimal scheduledMaintenanceAmount) {
        this.scheduledMaintenanceAmount = scheduledMaintenanceAmount;
        return this;
    }

    public BigDecimal getUnscheduledMaintenanceAmount() {
        return unscheduledMaintenanceAmount;
    }

    public AssetCosts withUnscheduledMaintenanceAmount(BigDecimal unscheduledMaintenanceAmount) {
        this.unscheduledMaintenanceAmount = unscheduledMaintenanceAmount;
        return this;
    }

    public BigDecimal getMonthlyFuelCost() {
        return monthlyFuelCost;
    }

    public AssetCosts withMonthlyFuelCost(BigDecimal monthlyFuelCost) {
        this.monthlyFuelCost = monthlyFuelCost;
        return this;
    }

    public Integer getMileageAuthorised() {
        return mileageAuthorised;
    }

    public AssetCosts withMileageAuthorised(Integer mileageAuthorised) {
        this.mileageAuthorised = mileageAuthorised;
        return this;
    }

    public BigDecimal getCostPerMileOverLimit() {
        return costPerMileOverLimit;
    }

    public AssetCosts withCostPerMileOverLimit(BigDecimal costPerMileOverLimit) {
        this.costPerMileOverLimit = costPerMileOverLimit;
        return this;
    }

    public BigDecimal getCostOfDisposal() {
        return costOfDisposal;
    }

    public AssetCosts withCostOfDisposal(BigDecimal costOfDisposal) {
        this.costOfDisposal = costOfDisposal;
        return this;
    }

    public BigDecimal getBuyoutAmount() {
        return buyoutAmount;
    }

    public AssetCosts withBuyoutAmount(BigDecimal buyoutAmount) {
        this.buyoutAmount = buyoutAmount;
        return this;
    }

    public String getCostCurrency() {
        return costCurrency;
    }

    public AssetCosts withCostCurrency(String costCurrency) {
        this.costCurrency = costCurrency;
        return this;
    }
}
