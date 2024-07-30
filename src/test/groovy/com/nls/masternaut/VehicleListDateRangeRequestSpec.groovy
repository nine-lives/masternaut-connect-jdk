package com.nls.masternaut

import com.nls.masternaut.util.RequestParameterMapper
import org.joda.time.LocalDateTime
import spock.lang.Specification

class VehicleListDateRangeRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        VehicleListDateRangeRequest request = new VehicleListDateRangeRequest()
                .withStartDate(LocalDateTime.parse("2024-04-01T07:50:40.233"))
                .withEndDate(LocalDateTime.parse("2024-05-01T07:50:40.233"))
                .withVehicleIds(Arrays.asList("V1", "V2"))
                .withGroupIds(Arrays.asList("G1", "G2"))

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.startDate == LocalDateTime.parse('2024-04-01T07:50:40.233')
        request.endDate == LocalDateTime.parse('2024-05-01T07:50:40.233')
        request.vehicleIds == ['V1', 'V2']
        request.groupIds == ['G1', 'G2']
        entity.startDate == '2024-04-01T07:50:40.233'
        entity.endDate == '2024-05-01T07:50:40.233'
        entity.vehicleIds == 'V1,V2'
        entity.groupIds == 'G1,G2'

        when:
        String result = mapper.write(request)

        then:
        result == '?startDate=2024-04-01T07%3A50%3A40.233&endDate=2024-05-01T07%3A50%3A40.233&vehicleIds=V1%2CV2&groupIds=G1%2CG2'
    }
}
