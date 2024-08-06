package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.DummyClient
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class LocationUpdateRequestSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a request to a payload"() {
        given:
        LocationUpdateRequest request = new LocationUpdateRequest(new DummyClient(), "1234")
                .withName("Masternaut Restaurant")
                .withCategoryName('Restaurants')
                .withCoordinate(new Coordinate()
                    .withLongitude(53.821729867523G)
                    .withLatitude(-1.3447768777930378G))
                .withRadius(0.001G)
                .withAddress(new Address()
                    .withRoadNumber('24')
                    .withRoad('Priory Park')
                    .withCity('Leeds')
                    .withPostCode('LE1 1EY')
                    .withCountry('England'))
                .withReference('ABC111')
                .withContact('Brad Woodenhouse')
                .withEmail('brad@test.com')
                .withPhoneNumber('+441234567')
                .withNotes('Company restaurant in Leeds')

        when:
        String json = mapper.writeValueAsString(request);
        Map<String, Object> entity = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        then:
        request.id == '1234'
        request.name == 'Masternaut Restaurant'
        request.categoryName == 'Restaurants'
        request.coordinate.longitude == 53.821729867523G
        request.coordinate.latitude == -1.3447768777930378G
        request.radius == 0.001G
        request.address.roadNumber == '24'
        request.address.road == 'Priory Park'
        request.address.city == 'Leeds'
        request.address.postCode == 'LE1 1EY'
        request.address.country == 'England'
        request.reference == 'ABC111'
        request.contact == 'Brad Woodenhouse'
        request.email == 'brad@test.com'
        request.phoneNumber == '+441234567'
        request.notes == 'Company restaurant in Leeds'
        entity.id == '1234'
        entity.name == 'Masternaut Restaurant'
        entity.categoryName == 'Restaurants'
        entity.coordinate.longitude == 53.821729867523G
        entity.coordinate.latitude == -1.3447768777930378G
        entity.radius == 0.001G
        entity.address.roadNumber == '24'
        entity.address.road == 'Priory Park'
        entity.address.city == 'Leeds'
        entity.address.postCode == 'LE1 1EY'
        entity.address.country == 'England'
        entity.reference == 'ABC111'
        entity.contact == 'Brad Woodenhouse'
        entity.email == 'brad@test.com'
        entity.phoneNumber == '+441234567'
        entity.notes == 'Company restaurant in Leeds'

//        when:
//        ObjectMapper om = ObjectMapperFactory.make(true)
//        LocationUpdateRequest result = om.readValue(om.writeValueAsString(entity), LocationUpdateRequest)
//
//        then:
//        result.name == 'Masternaut Restaurant'
//        result.categoryName == 'Restaurants'
//        result.coordinate.longitude == 53.821729867523G
//        result.coordinate.latitude == -1.3447768777930378G
//        result.radius == 0.001G
//        result.address.roadNumber == '24'
//        result.address.road == 'Priory Park'
//        result.address.city == 'Leeds'
//        result.address.postCode == 'LE1 1EY'
//        result.address.country == 'England'
//        result.reference == 'ABC111'
//        result.contact == 'Brad Woodenhouse'
//        result.email == 'brad@test.com'
//        result.phoneNumber == '+441234567'
//        result.notes == 'Company restaurant in Leeds'
    }
}