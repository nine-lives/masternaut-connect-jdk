package com.nls.masternaut

import com.nls.masternaut.util.RequestParameterMapper
import spock.lang.Specification

class VehicleListRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        VehicleListRequest request = new VehicleListRequest()
                .withPageSize(2)
                .withPageIndex(1)
                .withVehicleIds(Arrays.asList("V1", "V2"))
                .withGroupIds(Arrays.asList("G1", "G2"))
                .withName("Name")

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.pageSize == 2
        request.pageIndex == 1
        request.vehicleIds == ['V1', 'V2']
        request.groupIds == ['G1', 'G2']
        request.name == 'Name'
        entity.pageSize == '2'
        entity.pageIndex == '1'
        entity.vehicleIds == 'V1,V2'
        entity.groupIds == 'G1,G2'
        entity.name == 'Name'

        when:
        String result = mapper.write(request)

        then:
        result == '?vehicleIds=V1%2CV2&groupIds=G1%2CG2&name=Name&pageIndex=1&pageSize=2'
    }
}
