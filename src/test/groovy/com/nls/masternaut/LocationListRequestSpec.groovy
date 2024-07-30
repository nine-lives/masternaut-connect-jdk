package com.nls.masternaut

import com.nls.masternaut.util.RequestParameterMapper
import spock.lang.Specification

class LocationListRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        LocationListRequest request = new LocationListRequest()
                .withPageSize(2)
                .withPageIndex(1)
                .withName('Masternaut')
                .withAddress('Priory Park')
                .withCategory('Restaurants')
                .withReference('ABC123')
                .withPhoneNumber('4516732')

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.pageSize == 2
        request.pageIndex == 1
        request.name == 'Masternaut'
        request.address == 'Priory Park'
        request.category == 'Restaurants'
        request.reference == 'ABC123'
        request.phoneNumber == '4516732'
        entity.pageSize == '2'
        entity.pageIndex == '1'
        entity.name == 'Masternaut'
        entity.address == 'Priory Park'
        entity.category == 'Restaurants'
        entity.reference == 'ABC123'
        entity.phoneNumber == '4516732'

        when:
        String result = mapper.write(request)

        then:
        println(result)
        result == '?name=Masternaut&address=Priory+Park&category=Restaurants&reference=ABC123&phoneNumber=4516732&pageIndex=1&pageSize=2'
    }
}
