package com.nls.masternaut

import com.nls.masternaut.util.RequestParameterMapper
import spock.lang.Specification

class DriverListRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        DriverListRequest request = new DriverListRequest()
                .withPageSize(2)
                .withPageIndex(1)

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.pageSize == 2
        request.pageIndex == 1
        entity.pageSize == '2'
        entity.pageIndex == '1'

        when:
        String result = mapper.write(request)

        then:
        result == '?pageIndex=1&pageSize=2'
    }
}
