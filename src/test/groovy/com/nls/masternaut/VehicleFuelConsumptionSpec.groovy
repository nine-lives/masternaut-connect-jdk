package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class VehicleFuelConsumptionSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "assetId":"248849",
                "assetRegistration":"YA99RTE",
                "assetName":"YA99RTE",
                "energyType": "ICE",
                "fuelType": ["PETROL"],
                "distance": 401,
                "electricUsedkWh": 1.2,
                "gasUsedKg": 2.3,
                "fuelUsedLitres": 369.4,
                "fuelUsedPetrolEquivalentLitres" : 369.4,
                "co2": 97.828,
                "totalFuel": 369.4,
                "benchmarkConsumption": 340,
                "manufacturerConsumption":410
            }
       '''

        when:
        VehicleFuelConsumption entity = mapper.readValue(payload, VehicleFuelConsumption)

        then:
        entity.assetId == '248849'
        entity.assetRegistration == 'YA99RTE'
        entity.assetName == 'YA99RTE'
        entity.energyType == 'ICE'
        entity.fuelType == [FuelType.PETROL]
        entity.electricUsedkWh == 1.2G
        entity.gasUsedKg == 2.3G
        entity.fuelUsedLitres == 369.4G
        entity.fuelUsedPetrolEquivalentLitres == 369.4
        entity.co2 == 97.828G
        entity.totalFuel == 369.4G
        entity.benchmarkConsumption == 340G;
        entity.manufacturerConsumption == 410G
    }
}