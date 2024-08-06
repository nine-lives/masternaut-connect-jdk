package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.DummyClient
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class LocationCategoryUpdateRequestSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a request to a payload"() {
        given:
        LocationCategoryUpdateRequest request = new LocationCategoryUpdateRequest(new DummyClient(), '535917de60b113440f8f3df4')
                .withName("Masternaut customer")
                .withIcon("circle-red")

        when:
        String json = mapper.writeValueAsString(request);
        Map<String, Object> entity = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        then:
        request.id == '535917de60b113440f8f3df4'
        entity.id == '535917de60b113440f8f3df4'
        request.name == 'Masternaut customer'
        entity.name == 'Masternaut customer'
        request.icon == 'circle-red'
        entity.icon == 'circle-red'
        entity.client == null

//        when:
//        ObjectMapper om = ObjectMapperFactory.make(true)
//        LocationCategoryUpdateRequest result = om.readValue(om.writeValueAsString(entity), LocationCategoryUpdateRequest)
//
//        then:
//        result.id == request.id
//        result.name == request.name
//        result.icon == request.icon
    }

    def "I can populate from a LocationCategory object"() {

        given:
        String payload = '''
                {
                    "id": "51d3e8e91f2659398989ad2a",
                    "name": "Sales Home",
                    "icon": "circle-red",
                    "locationsCount": 16
                }
           '''
        LocationCategory category = mapper.readValue(payload, LocationCategory)

        when:
        LocationCategoryUpdateRequest request = new LocationCategoryUpdateRequest(new DummyClient(), '51d3e8e91f2659398989ad2a')
            .withLocationCategory(category);

        then:
        request.id == '51d3e8e91f2659398989ad2a'
        request.name == 'Sales Home'
        request.icon == 'circle-red'
    }
}