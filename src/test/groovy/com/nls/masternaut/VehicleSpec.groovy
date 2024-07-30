package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import spock.lang.Specification

class VehicleSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
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

        when:
        Vehicle entity = mapper.readValue(payload, Vehicle)

        then:
        entity.id == '54082'
        entity.registration == 'YA99RGE'
        entity.name == 'CAR YA99RGE'
        entity.type == VehicleType.CAR
        entity.groupId == '5315b03360b12054ebd4a8c5'
        entity.groupName == 'Masternaut'
        entity.assetTypeGroup == 'CARSANDLCV'
        entity.odometerValue == 40378
        entity.odometerType == OdometerType.CAN
        entity.odometerLastModified == LocalDateTime.parse('2024-06-29T10:37:34.000')
        entity.make == 'Mercedes Trucks'
        entity.model == 'Axor – Euro 2'
        entity.tags == ['Crane']
        entity.featureTags == ["FAULT_MBOX_214","DEFECTS"]
        entity.defaultDriverId == '3454072'
        entity.status == VehicleStatus.IN_CIRCULATION
        entity.idlingFidelity == 1
        entity.fuelType == [FuelType.ELECTRIC, FuelType.PETROL]
        entity.engineTotalHours == 456.8
        entity.engineTotalHoursType == EngineTotalHoursType.ESTIMATED
        entity.engineTotalHoursLastModified == LocalDateTime.parse('2024-07-29T10:37:34.000')
        entity.assetCosts.yearOfManufacture == 2010
        entity.assetCosts.ownershipStartDate == LocalDate.parse('2012-01-02')
        entity.assetCosts.ownershipEndDate == LocalDate.parse('2015-03-04')
        entity.assetCosts.purchaseCost == 123456.89G
        entity.assetCosts.monthlyInsuranceCost == 123.45G
        entity.assetCosts.monthlyMaintenanceCost == 34.56G
        entity.assetCosts.scheduledMaintenanceAmount == 345.67G
        entity.assetCosts.unscheduledMaintenanceAmount == 456.78G
        entity.assetCosts.monthlyFuelCost == 456.78G
        entity.assetCosts.mileageAuthorised == 6789
        entity.assetCosts.costPerMileOverLimit == 1.23G
        entity.assetCosts.costOfDisposal == 8765.43G
        entity.assetCosts.buyoutAmount == 9876.54G
        entity.assetCosts.costCurrency == 'GBP'
    }


    def "I can convert a JSON page payload to pages of the entity"() {
        given:
        String payload = '''
            {
                "totalPages": 1,
                "totalCount": 2,
                "items":[
                    { 
                        "id":"54070",
                        "registration": "YA99RTE",
                        "name": "YA99RTE",
                        "type": "HGV",
                        "groupId": "5315b03360b12054ebd4a8c5",
                        "groupName": "Masternaut",
                        "odometerValue": "45678",
                        "odometerType": "GPS",
                        "make": "Volkswagen",
                        "model": "Caddy",
                        "status": "IN_CIRCULATION",
                        "idlingFidelity": 1,
                        "energyType": "ICE",
                        "fuelType": ["PETROL"]
                    },
                    { 
                        "id":"54082",
                        "registration": "YA99RGE",
                        "name": "YA99RGE",
                        "type": "CAR",
                        "groupId":"5315b03360b12054ebd4a8c5",
                        "groupName":"Masternaut",
                        "odometerValue":"40378",
                        "odometerType":"CAN",
                        "make": "Mercedes Trucks",
                        "model": "Axor – Euro 2",
                        "tags": ["Crane"],
                        "defaultDriverId":"3454072",
                        "status": "IN_CIRCULATION",
                        "assetCosts": {"yearOfManufacture" : 2010, "costCurrency":"GBP"},
                        "idlingFidelity": 1,
                        "energyType": "HEV",
                        "fuelType": ["ELECTRIC","PETROL"],
                        "engineTotalHours": "456.8",
                        "engineTotalHoursType": "ESTIMATED"
                    }
                ]
            }
       '''

        when:
        PageResponse<Vehicle> entity = mapper.readValue(payload, new TypeReference<PageResponse<Vehicle>>() {})

        then:
        entity.totalCount == 2
        entity.totalPages == 1
        entity.items.size() == 2
        entity.items[0].id == '54070'
        entity.items[1].id == '54082'
        entity.items[1].assetCosts.yearOfManufacture == 2010
    }
}