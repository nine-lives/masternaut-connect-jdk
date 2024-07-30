package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class DriverFuelConsumptionSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "driverId":"181095",
                "driverName":"Masternaut Driver C",
                "vehicles": [
                    {
                    "assetId":"248849",
                    "assetRegistration":"Masternaut C",
                    "assetName":"Masternaut C",
                    "distance":167.4,
                    "energyType": "ICE",
                    "fuelType": ["PETROL"],
                    "electricUsedkWh": 1.2,
                    "gasUsedKg": 2.3,
                    "fuelUsedLitres": 34.0,
                    "fuelUsedPetrolEquivalentLitres" : 34.0,
                    "co2":97.828,
                    "totalFuel":34.0,
                    "benchmarkConsumption":12.427423844746679
                    }
                ]
            }
       '''

        when:
        DriverFuelConsumption entity = mapper.readValue(payload, DriverFuelConsumption)

        then:
        entity.driverId == '181095'
        entity.driverName == 'Masternaut Driver C'
        entity.vehicles.size() == 1
        entity.vehicles[0].assetId == '248849'
        entity.vehicles[0].assetRegistration == 'Masternaut C'
        entity.vehicles[0].assetName == 'Masternaut C'
        entity.vehicles[0].energyType == 'ICE'
        entity.vehicles[0].fuelType == [FuelType.PETROL]
        entity.vehicles[0].electricUsedkWh == 1.2G
        entity.vehicles[0].gasUsedKg == 2.3G
        entity.vehicles[0].fuelUsedLitres == 34.0G
        entity.vehicles[0].fuelUsedPetrolEquivalentLitres == 34.0
        entity.vehicles[0].co2 == 97.828G
        entity.vehicles[0].totalFuel == 34.0G
        entity.vehicles[0].benchmarkConsumption == 12.427423844746679G;
        entity.vehicles[0].manufacturerConsumption == null
    }
}