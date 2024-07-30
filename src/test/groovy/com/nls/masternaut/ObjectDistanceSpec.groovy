package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class ObjectDistanceSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "id": "5315b03360b12054ebd4ef45",
                "name": "YA99 RTE",
                "straightLineDistanceKilometers": 401.243333
            }
       '''

        when:
        ObjectDistance entity = mapper.readValue(payload, ObjectDistance)

        then:
        entity.id == '5315b03360b12054ebd4ef45'
        entity.name == 'YA99 RTE'
        entity.straightLineDistanceKilometers == 401.243333G
    }
}