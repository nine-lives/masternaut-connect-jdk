package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.DummyClient
import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDate
import spock.lang.Specification

class VehicleUpdateRequestSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a request to a payload"() {
        given:
        VehicleUpdateRequest request = new VehicleUpdateRequest(new DummyClient(), '12345')
                .withGroupId("5315b03360b12054ebd4a8c5")
                .withName("Car name")
                .withTags(Arrays.asList("T1", "T2"))
                .withType(VehicleType.AMBULANCE)
                .withStatus(VehicleStatus.IN_CIRCULATION)
                .withAssetCosts(new AssetCosts()
                    .withYearOfManufacture(2010)
                    .withOwnershipStartDate(LocalDate.parse('2012-01-02'))
                    .withOwnershipEndDate(LocalDate.parse('2015-03-04'))
                    .withPurchaseCost(123456.89G)
                    .withMonthlyInsuranceCost(123.45G)
                    .withMonthlyMaintenanceCost(35.56G)
                    .withScheduledMaintenanceAmount(345.67G)
                    .withUnscheduledMaintenanceAmount(456.78)
                    .withMonthlyFuelCost(456.78)
                    .withMileageAuthorised(6789)
                    .withCostPerMileOverLimit(1.23G)
                    .withCostOfDisposal(8765.43G)
                    .withBuyoutAmount(9876.54)
                    .withCostCurrency('GBP'))

        when:
        String json = mapper.writeValueAsString(request)
        Map<String, Object> entity = mapper.readValue(json, new TypeReference<Map<String, Object>>() {})

        then:
        entity.name == 'Car name'
        entity.groupId == '5315b03360b12054ebd4a8c5'
        entity.tags == ['T1', 'T2']
        entity.status == 'IN_CIRCULATION'
        entity.type == 'AMBULANCE'
        entity.assetCosts.yearOfManufacture == 2010
        entity.assetCosts.ownershipStartDate == '2012-01-02'
        entity.assetCosts.ownershipEndDate == '2015-03-04'
        entity.assetCosts.purchaseCost == 123456.89G
        entity.assetCosts.monthlyInsuranceCost == 123.45G
        entity.assetCosts.monthlyMaintenanceCost == 35.56G
        entity.assetCosts.scheduledMaintenanceAmount == 345.67G
        entity.assetCosts.unscheduledMaintenanceAmount == 456.78G
        entity.assetCosts.monthlyFuelCost == 456.78G
        entity.assetCosts.mileageAuthorised == 6789
        entity.assetCosts.costPerMileOverLimit == 1.23G
        entity.assetCosts.costOfDisposal == 8765.43G
        entity.assetCosts.buyoutAmount == 9876.54G
        entity.assetCosts.costCurrency == 'GBP'

//        when:
//        ObjectMapper om = ObjectMapperFactory.make(true)
//        println(om.writeValueAsString(entity))
//        VehicleUpdateRequest result = om.readValue(om.writeValueAsString(entity), VehicleUpdateRequest)
//
//        then:
//        result.name == 'Car name'
//        result.groupId == '5315b03360b12054ebd4a8c5'
//        result.tags == ['T1', 'T2']
//        result.status == VehicleStatus.IN_CIRCULATION
//        result.type == VehicleType.AMBULANCE
//        result.assetCosts.yearOfManufacture == 2010
//        result.assetCosts.ownershipStartDate == LocalDate.parse('2012-01-02')
//        result.assetCosts.ownershipEndDate == LocalDate.parse('2015-03-04')
//        result.assetCosts.purchaseCost == 123456.89G
//        result.assetCosts.monthlyInsuranceCost == 123.45G
//        result.assetCosts.monthlyMaintenanceCost == 35.56G
//        result.assetCosts.scheduledMaintenanceAmount == 345.67G
//        result.assetCosts.unscheduledMaintenanceAmount == 456.78G
//        result.assetCosts.monthlyFuelCost == 456.78G
//        result.assetCosts.mileageAuthorised == 6789
//        result.assetCosts.costPerMileOverLimit == 1.23G
//        result.assetCosts.costOfDisposal == 8765.43G
//        result.assetCosts.buyoutAmount == 9876.54G
//        result.assetCosts.costCurrency == 'GBP'
    }

    def "I can create a request from a vehicle"() {
        given:
        String payload = '''
            {
                "id": "54082",
                "registration": "YA99RGE",
                "name": "CAR YA99RGE",
                "type": "CAR",
                "groupId":"5315b03360b12054ebd4a8c5",
                "groupName":"Masternaut",
                "odometerValue":"40378",
                "odometerType":"CAN",
                "odometerLastModified":"2024-06-29T10:37:34.000",
                "make": "Mercedes Trucks",
                "model": "Axor – Euro 2",
                "assetTypeGroup":"CARSANDLCV",
                "tags": ["Crane"],
                "featureTags": ["FAULT_MBOX_214","DEFECTS"],
                "defaultDriverId":"3454072",
                "status": "IN_CIRCULATION",
                "assetCosts": {
                    "yearOfManufacture" : 2010, 
                    "ownershipStartDate" : "2012-01-02", 
                    "ownershipEndDate" : "2015-03-04", 
                    "purchaseCost" : 123456.89, 
                    "monthlyInsuranceCost" : "123.45", 
                    "monthlyMaintenanceCost" : "34.56", 
                    "scheduledMaintenanceAmount" : 345.67, 
                    "unscheduledMaintenanceAmount" : 456.78, 
                    "monthlyFuelCost" : 456.78, 
                    "mileageAuthorised" : 6789, 
                    "costPerMileOverLimit" : 1.23, 
                    "costOfDisposal" : 8765.43, 
                    "buyoutAmount" : 9876.54, 
                    "costCurrency":"GBP"
                    },
                "idlingFidelity": 1,
                "energyType": "HEV",
                "fuelType": ["ELECTRIC","PETROL"],
                "engineTotalHours": "456.8",
                "engineTotalHoursType": "ESTIMATED",
                "engineTotalHoursLastModified":"2024-07-29T10:37:34.000"
            }
        '''

        Vehicle entity = mapper.readValue(payload, Vehicle)

        when:
        VehicleUpdateRequest request = new VehicleUpdateRequest(new DummyClient(), "1234")
            .withVehicle(entity)

        then:
        request.name == 'CAR YA99RGE'
        request.groupId == '5315b03360b12054ebd4a8c5'
        request.tags == ['Crane']
        request.status == VehicleStatus.IN_CIRCULATION
        request.type == VehicleType.CAR
        request.assetCosts.yearOfManufacture == 2010
        request.assetCosts.ownershipStartDate == LocalDate.parse('2012-01-02')
        request.assetCosts.ownershipEndDate == LocalDate.parse('2015-03-04')
        request.assetCosts.purchaseCost == 123456.89G
        request.assetCosts.monthlyInsuranceCost == 123.45G
        request.assetCosts.monthlyMaintenanceCost == 34.56G
        request.assetCosts.scheduledMaintenanceAmount == 345.67G
        request.assetCosts.unscheduledMaintenanceAmount == 456.78G
        request.assetCosts.monthlyFuelCost == 456.78G
        request.assetCosts.mileageAuthorised == 6789
        request.assetCosts.costPerMileOverLimit == 1.23G
        request.assetCosts.costOfDisposal == 8765.43G
        request.assetCosts.buyoutAmount == 9876.54G
        request.assetCosts.costCurrency == 'GBP'
    }
}