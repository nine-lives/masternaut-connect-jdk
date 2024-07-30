
package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class LocationCategorySpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "id": "51d3e8e91f2659398989ad2a",
                "name": "Sales Home",
                "icon": "circle-red",
                "locationsCount": 16
            }
       '''

        when:
        LocationCategory entity = mapper.readValue(payload, LocationCategory)

        then:
        entity.id == '51d3e8e91f2659398989ad2a'
        entity.name == 'Sales Home'
        entity.icon == 'circle-red'
        entity.locationsCount == 16
    }
}