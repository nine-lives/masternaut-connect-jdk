package com.nls.masternaut

import com.nls.masternaut.util.RequestParameterMapper
import org.joda.time.LocalDateTime
import spock.lang.Specification

class VehicleDateRangeRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        VehicleDateRangeRequest request = new VehicleDateRangeRequest()
                .withStartDate(LocalDateTime.parse("2024-04-01T07:50:40.233"))
                .withEndDate(LocalDateTime.parse("2024-05-01T07:50:40.233"))
                .withVehicleId("V1")
                .withGroupId("G1")

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.startDate == LocalDateTime.parse('2024-04-01T07:50:40.233')
        request.endDate == LocalDateTime.parse('2024-05-01T07:50:40.233')
        request.vehicleId == 'V1'
        request.groupId == 'G1'
        entity.startDate == '2024-04-01T07:50:40.233'
        entity.endDate == '2024-05-01T07:50:40.233'
        entity.vehicleId == 'V1'
        entity.groupId == 'G1'

        when:
        String result = mapper.write(request)

        then:
        result == '?startDate=2024-04-01T07%3A50%3A40.233&endDate=2024-05-01T07%3A50%3A40.233&vehicleId=V1&groupId=G1'
    }
}
