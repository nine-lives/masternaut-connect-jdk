package com.nls.masternaut

import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDateTime

import java.util.concurrent.TimeUnit

//@Ignore
class LatestLivePositionIntegrationSpec extends BaseIntegrationSpec {
    private List<LivePosition> positionList

    def "I can get latest live positions"() {
        when:
        LatestLivePositionList response = connect.tracking().latest().fetch();

        then:
        response.totalCount > 0;

        println(ObjectMapperFactory.make().writeValueAsString(response));

        cleanup:
        positionList = response.items
    }

    def "I can get live positions by vehicle ids"() {
        given:
        List<LivePosition> positions = getPositionList()
        List<String> vehicleIds = ((positions*.assetId as Set) as List).subList(0, 2)

        when:
        LatestLivePositionList response = connect.tracking().latest()
                .withVehicleIds(vehicleIds)
                .fetch()


        then:
        response.items.size() == 2
        response.items.each {
            assert vehicleIds.contains(it.assetId)
        }
    }

    def "I can get live positions by driver ids"() {
        given:
        List<LivePosition> positions = getPositionList()
        List<String> driverIds = ((positions*.driverId as Set) as List).subList(0, 2)

        when:
        LatestLivePositionList response = connect.tracking().latest()
                .withDriverIds(driverIds)
                .fetch()

        then:
        response.items.size() == 2
        response.items.each {
            assert driverIds.contains(it.driverId)
        }
    }

    def "I can get live positions by group ids"() {
        given:
        List<LivePosition> positions = getPositionList()
        List<String> groupIds = ((positions*.assetGroupId as Set) as List).subList(0, 2)

        when:
        LatestLivePositionList response = connect.tracking().latest()
                .withGroupIds(groupIds)
                .fetch()

        then:
        response.items.size() >= 2
        response.items.each {
            assert groupIds.contains(it.assetGroupId)
        }
    }

    def "I can refresh latest live positions"() {
        given:
        LatestLivePositionList initial = connect.tracking().latest().fetch()
        sleep(15000)

        when:
        LatestLivePositionList response = initial.refresh();
        println(ObjectMapperFactory.make().writeValueAsString(response))

        then:
        initial.processedDateTime < response.processedDateTime
        response.items.each {
            LivePosition initialPosition = initial.items.find {ia -> it.assetId == ia.assetId }
            assert initialPosition.date < it.date
        } || response.totalCount == 0
    }

    def "I can poll for latest live positions"() {
        given:
        int count = 0
        LocalDateTime time = new LocalDateTime().minusMonths(1)
        boolean processedTimeIsAfterLast = true
        boolean hasError = false

        LatestLivePositionListener listener = new LatestLivePositionListener() {
            @Override
            void onUpdate(LatestLivePositionList positions) {
                println("positions received")
                count++
                if (processedTimeIsAfterLast) {
                    processedTimeIsAfterLast = positions.processedDateTime > time
                    time = positions.processedDateTime
                }
            }

            @Override
            void onError(Exception e) {
                hasError = true
            }
        }

        LatestLivePositionPoller poller = LatestLivePositionPoller.builder(connect.tracking().latest())
                .withFixedRate(true)
                .withPollingInterval(15, TimeUnit.SECONDS)
                .build()
        poller.addListener(listener)

        when:
        poller.start(0)
        sleep(35000)
        poller.stop()

        then:
        count == 3
        processedTimeIsAfterLast
        !hasError

        cleanup:
        poller.shutdown()
    }

    private List<LivePosition> getPositionList() {
        positionList = positionList ?: connect.tracking().live().fetch();
    }
}
