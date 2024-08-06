package com.nls.masternaut

import com.nls.masternaut.util.DummyClient
import com.nls.masternaut.util.RequestParameterMapper
import org.joda.time.LocalDateTime
import spock.lang.Specification

class LatestLivePositionRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        LatestLivePositionRequest request = new LatestLivePositionRequest(new DummyClient())
                .withFromDateTime(LocalDateTime.parse("2017-04-20T07:50:40.233"))
                .withVehicleIds(Arrays.asList("V1", "V2"))
                .withDriverIds(Arrays.asList("D1", "D2"))
                .withGroupIds(Arrays.asList("G1", "G2"))
                .withShowAllVehicleStatus(true)
                .withHideSpeedIfSpeedSensitive(true)

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.fromDateTime == LocalDateTime.parse('2017-04-20T07:50:40.233')
        request.vehicleIds == ['V1', 'V2']
        request.driverIds == ['D1', 'D2']
        request.groupIds == ['G1', 'G2']
        request.showAllVehicleStatus
        request.hideSpeedIfSpeedSensitive
        entity.fromDateTime == '2017-04-20T07:50:40.233'
        entity.vehicleIds == 'V1,V2'
        entity.driverIds == 'D1,D2'
        entity.groupIds == 'G1,G2'
        entity.showAllVehicleStatus == 'true'
        entity.hideSpeedIfSpeedSensitive == 'true'
        entity.client == null

        when:
        String result = mapper.write(request)

        then:
        result == '?fromDateTime=2017-04-20T07%3A50%3A40.233&vehicleIds=V1%2CV2&driverIds=D1%2CD2&groupIds=G1%2CG2&showAllVehicleStatus=true&hideSpeedIfSpeedSensitive=true'
    }
}
