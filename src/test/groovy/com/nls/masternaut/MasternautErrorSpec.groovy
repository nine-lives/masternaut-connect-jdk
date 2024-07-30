package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class MasternautErrorSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can covert a JSON payload with message error to the entity"() {
        given:
        String payload = '''
            {
                "errorCode": "ERRCODE",
                "errorMessage": "error message and corrective actions to take"
            }
       '''

        when:
        MasternautError entity = mapper.readValue(payload, MasternautError)

        then:
        entity.code == 'ERRCODE'
        entity.message == 'error message and corrective actions to take'
    }
}