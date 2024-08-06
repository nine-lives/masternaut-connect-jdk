package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.DummyClient
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class GroupAddRequestSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a request to a payload"() {
        given:
        GroupAddRequest request = new GroupAddRequest(new DummyClient())
                .withName("a new group")
                .withParentId("5045939e60b13e9f54b1e047")

        when:
        String json = mapper.writeValueAsString(request);
        Map<String, Object> entity = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        then:
        request.name == 'a new group'
        entity.name == 'a new group'
        request.parentId == '5045939e60b13e9f54b1e047'
        entity.parentId == '5045939e60b13e9f54b1e047'
        entity.client == null

//        when:
//        ObjectMapper om = ObjectMapperFactory.make(true)
//        GroupAddRequest result = om.readValue(om.writeValueAsString(entity), GroupAddRequest)
//
//        then:
//        result.name == request.name
    }
}