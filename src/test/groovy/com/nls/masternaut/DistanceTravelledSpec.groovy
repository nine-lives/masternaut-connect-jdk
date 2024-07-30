package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class DistanceTravelledSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "assetId": "54072",
                "assetRegistration": "YA99RTE",
                "assetName": "YA99RTE",
                "groupName": "Leeds",
                "distance": 401.888,
                "odometerType": "GPS"
            }
       '''

        when:
        DistanceTravelled entity = mapper.readValue(payload, DistanceTravelled)

        then:
        entity.assetId == '54072'
        entity.assetRegistration == 'YA99RTE'
        entity.assetName == 'YA99RTE'
        entity.groupName == 'Leeds'
        entity.distance == 401.888G
        entity.odometerType == OdometerType.GPS
    }
}