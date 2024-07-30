package com.nls.masternaut

import com.nls.masternaut.util.ObjectMapperFactory

//@Ignore
class LatestLivePositionIntegrationSpec extends BaseIntegrationSpec {
    private List<LivePosition> positionList

    def "I can get latest live positions"() {
        when:
        LatestLivePositionList response = connect.tracking().latest();

        then:
        response.totalCount > 0;

        println(ObjectMapperFactory.make().writeValueAsString(response));

        cleanup:
        positionList = response.items
    }

//    def "I can get live positions by vehicle ids"() {
//        given:
//        List<LivePosition> positions = getPositionList()
//        List<String> vehicleIds = ((positions*.assetId as Set) as List).subList(0, 2)
//
//        when:
//        List<LivePosition> response = connect.tracking().live(new LivePositionRequest()
//                .withVehicleIds(vehicleIds))
//
//        then:
//        response.size() == 2
//        response.each {
//            assert vehicleIds.contains(it.assetId)
//        }
//    }
//
//    def "I can get live positions by driver ids"() {
//        given:
//        List<LivePosition> positions = getPositionList()
//        List<String> driverIds = ((positions*.driverId as Set) as List).subList(0, 2)
//
//        when:
//        List<LivePosition> response = connect.tracking().live(new LivePositionRequest()
//                .withDriverIds(driverIds))
//
//        then:
//        response.size() == 2
//        response.each {
//            assert driverIds.contains(it.driverId)
//        }
//    }
//
//    def "I can get live positions by group ids"() {
//        given:
//        List<LivePosition> positions = getPositionList()
//        List<String> groupIds = ((positions*.assetGroupId as Set) as List).subList(0, 2)
//
//        when:
//        List<LivePosition> response = connect.tracking().live(new LivePositionRequest()
//                .withGroupIds(groupIds))
//
//        then:
//        response.size() > 2
//        response.each {
//            assert groupIds.contains(it.assetGroupId)
//        }
//    }

    private List<LivePosition> getPositionList() {
        positionList = positionList ?: connect.tracking().live();
    }
}
