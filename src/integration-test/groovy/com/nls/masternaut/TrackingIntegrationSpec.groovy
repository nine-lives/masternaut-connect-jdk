package com.nls.masternaut

import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDate


//@Ignore
class TrackingIntegrationSpec extends BaseIntegrationSpec {
    def "I can get journey summaries"() {
        when:
        List<DistanceTravelled> response = connect.tracking().getJourneySummaries(new VehicleListDateRangeRequest()
            .withStartDate(LocalDate.now().minusMonths(1).withDayOfMonth(1).toDateTimeAtStartOfDay().toLocalDateTime())
            .withEndDate(LocalDate.now().withDayOfMonth(1).minusDays(1).toDateTimeAtStartOfDay().toLocalDateTime().minusSeconds(1)))

        then:
        response.size() > 0;
        println(ObjectMapperFactory.make().writeValueAsString(response));

    }
}
