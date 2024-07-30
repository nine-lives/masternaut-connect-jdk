
package com.nls.masternaut

import com.nls.masternaut.util.RequestParameterMapper
import org.joda.time.LocalDateTime
import spock.lang.Specification

class FindNearestRequestSpec extends Specification {
    private RequestParameterMapper mapper = new RequestParameterMapper()

    def "I can convert a request to parameters"() {
        given:
        FindNearestRequest request = new FindNearestRequest()
                .withRadius(11)
                .withMaximumResultsToReturn(10)
                .withRoadNumber("48")
                .withRoad("London Road")
                .withCity("Elysium")
                .withPostCode("EL1 5EY")
                .withCountry("England")
                .withLatitude(new BigDecimal("53.8304"))
                .withLongitude(new BigDecimal("-1.3421"))
                .withLastMovedStartDateTime(LocalDateTime.parse("2024-04-20T07:50:40.233"))
                .withLastMovedEndDateTime(LocalDateTime.parse("2025-04-20T07:50:40.233"))

        when:
        Map<String, String> entity = mapper.writeToMap(request)

        then:
        request.radius == 11
        request.maximumResultsToReturn == 10
        request.roadNumber == '48'
        request.road == 'London Road'
        request.city == 'Elysium'
        request.postCode == 'EL1 5EY'
        request.country == 'England'
        request.latitude == 53.8304G
        request.longitude == -1.3421G
        request.lastMovedStartDateTime == LocalDateTime.parse('2024-04-20T07:50:40.233')
        request.lastMovedEndDateTime == LocalDateTime.parse('2025-04-20T07:50:40.233')
        entity.radius == '11'
        entity.maximumResultsToReturn == '10'
        entity.roadNumber == '48'
        entity.road == 'London Road'
        entity.city == 'Elysium'
        entity.postCode == 'EL1 5EY'
        entity.country == 'England'
        entity.latitude == '53.8304'
        entity.longitude == '-1.3421'
        entity.lastMovedStartDateTime == '2024-04-20T07:50:40.233'
        entity.lastMovedEndDateTime == '2025-04-20T07:50:40.233'

        when:
        String result = mapper.write(request)

        then:
        result == '?radius=11&maximumResultsToReturn=10&roadNumber=48&road=London+Road&city=Elysium&postCode=EL1+5EY&country=England&latitude=53.8304&longitude=-1.3421&lastMovedStartDateTime=2024-04-20T07%3A50%3A40.233&lastMovedEndDateTime=2025-04-20T07%3A50%3A40.233'
    }
}
