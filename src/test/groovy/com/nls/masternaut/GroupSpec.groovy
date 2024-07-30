package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class GroupSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "id": "5045939e60",
                "name": "Leeds",
                "parentId": "5045939e60b13e9f54b1e047",
                "path": "5045939e60b13e9f54b1e046.5045939e60b13e9f54b1e047.5045939e60",
                "countVehicles": 5,
                "countDrivers": 9
            }
       '''

        when:
        Group entity = mapper.readValue(payload, Group)

        then:
        entity.id == '5045939e60'
        entity.name == 'Leeds'
        entity.parentId == '5045939e60b13e9f54b1e047'
        entity.path == '5045939e60b13e9f54b1e046.5045939e60b13e9f54b1e047.5045939e60'
        entity.countVehicles == 5
        entity.countDrivers == 9
    }


    def "I can convert a JSON payload from a post to the entity"() {
        given:
        String payload = '''
            {
                "id": "652939f1e8b9ea0596fd2f2a",
                "name": "A new group name",
                "parentId": "5045939e60b13e9f54b1e047",
                "path": "5045939e60b13e9f54b1e046.5045939e60b13e9f54b1e047.5045939e60",
                "vehicleIds": ["54070", "54082"],
                "personIds": ["347626", "347627", "347628"]
            }
       '''

        when:
        Group entity = mapper.readValue(payload, Group)

        then:
        entity.id == '652939f1e8b9ea0596fd2f2a'
        entity.name == 'A new group name'
        entity.parentId == '5045939e60b13e9f54b1e047'
        entity.path == '5045939e60b13e9f54b1e046.5045939e60b13e9f54b1e047.5045939e60'
        entity.vehicleIds.size() == 2
        entity.vehicleIds[0] == '54070'
        entity.vehicleIds[1] == '54082'
        entity.personIds.size() == 3
        entity.personIds[0] == '347626'
        entity.personIds[1] == '347627'
        entity.personIds[2] == '347628'
        entity.countVehicles == 2
        entity.countDrivers == 3
    }
}