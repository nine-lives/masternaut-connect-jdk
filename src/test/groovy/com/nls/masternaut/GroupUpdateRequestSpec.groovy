package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.DummyClient
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class GroupUpdateRequestSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a request to a payload"() {
        given:
        GroupUpdateRequest request = new GroupUpdateRequest(new DummyClient(), '652939f1e8b9ea0596fd2f2a')
                .withName("A new group name")
                .withParentId("5045939e60b13e9f54b1e047")
                .withVehicleIds(Arrays.asList('54070', '54082'))
                .withPersonIds(Arrays.asList('347626', '347627'))

        when:
        String json = mapper.writeValueAsString(request);
        Map<String, Object> entity = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        then:
        request.id == '652939f1e8b9ea0596fd2f2a'
        entity.id == '652939f1e8b9ea0596fd2f2a'
        request.name == 'A new group name'
        entity.name == 'A new group name'
        request.parentId == '5045939e60b13e9f54b1e047'
        entity.parentId == '5045939e60b13e9f54b1e047'
        request.vehicleIds.size() == 2
        entity.vehicleIds.size() == 2
        request.vehicleIds == Arrays.asList('54070', '54082')
        entity.vehicleIds == Arrays.asList('54070', '54082')
        request.personIds.size() == 2
        entity.personIds.size() == 2
        request.personIds == Arrays.asList('347626', '347627')
        entity.personIds == Arrays.asList('347626', '347627')
        entity.client == null

//        when:
//        ObjectMapper om = ObjectMapperFactory.make(true)
//        println(om.writeValueAsString(entity))
//        GroupUpdateRequest result = om.readValue(om.writeValueAsString(entity), GroupUpdateRequest)
//
//        then:
//        result.id == request.id
//        result.name == request.name
//        result.parentId == request.parentId
//        result.vehicleIds.size() == 2
//        result.vehicleIds == request.vehicleIds
//        result.personIds.size() == 2
//        result.personIds == request.personIds
    }
}