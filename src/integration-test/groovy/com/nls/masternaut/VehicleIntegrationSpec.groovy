package com.nls.masternaut

import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

//@Ignore
class VehicleIntegrationSpec extends BaseIntegrationSpec {
    private List<Vehicle> vehicleList

    def "I can list vehicles"() {
        when:
        Page<Vehicle> response = connect.vehicles().list()

        then:
        response.totalCount > 0;
        response.totalPages == null;
        response.items.size() > 0;
        response.items[0].id != null
        response.items[0].name != null

        response.items.each { println(it.id + ' ' + it.name + ' ' + it.groupId + ' ' + it.defaultDriverId)}

        cleanup:
        vehicleList = response.items
    }

    def "I can page vehicles"() {
        when:
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest()
            .withPageIndex(0)
            .withPageSize(3))

        then:
        response.totalCount > 3;
        response.totalPages == (int) (response.totalCount / 3) + (response.totalCount % 3 ? 1 : 0);
        response.items.size() == 3;

        when:
        Page<Vehicle> response1 = connect.vehicles().list(new VehicleListRequest()
                .withPageIndex(0)
                .withPageSize(2))

        then:
        response1.totalCount == response.totalCount;
        response1.totalPages == (int) (response.totalCount / 2) + (response.totalCount % 2 ? 1 : 0);
        response1.items.size() == 2;
        response1.items[0].id == response.items[0].id
        response1.items[1].id == response.items[1].id

        when:
        Page<Vehicle> response2 = connect.vehicles().list(new VehicleListRequest()
                .withPageIndex(1)
                .withPageSize(2))

        then:
        response2.totalCount == response.totalCount;
        response2.totalPages == (int) (response.totalCount / 2) + (response.totalCount % 2 ? 1 : 0);
        response2.items.size() == 2;
        response2.items[0].id == response.items[2].id

        when:
        Page<Vehicle> response3 = connect.vehicles().list(new VehicleListRequest()
                .withPageIndex(1)
                .withPageSize(1))

        then:
        response3.totalCount == response.totalCount;
        response3.totalPages == response.totalCount;
        response3.items.size() == 1;
        response3.items[0].id == response.items[1].id

        then:
        response.totalCount > 3;
        response.totalPages == (int) (response.totalCount / 3) + (response.totalCount % 3 ? 1 : 0);
        response.items.size() == 3;

        when:
        Page<Vehicle> response4 = connect.vehicles().list(new VehicleListRequest()
                .withPageIndex(0)
                .withPageSize(5))

        then:
        response4.totalCount == response.totalCount;
        response4.totalPages == (int) (response.totalCount / 5) + (response.totalCount % 5 ? 1 : 0);
        response4.items.size() == 5;
    }

    def "I can get next vehicles"() {
        given:
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest()
                .withPageSize(5))
        Set<String> firstPageIds = response.items*.id as Set

        when:
        Page<Vehicle> next = response.next()

        then:
        next.totalCount == response.totalCount
        next.totalPages == response.totalPages
        next.items.size() > 0
        next.items.each {
            assert !firstPageIds.contains(it.id)
        }
    }

    def "I can get next vehicles with filter"() {
        given:
        List<String> vehicleIds = getVehicleList().subList(0, 10)*.id
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest()
                .withPageIndex(0)
                .withPageSize(5)
                .withVehicleIds(vehicleIds))
        Set<String> firstPageIds = response.items*.id as Set

        when:
        Page<Vehicle> next = response.next()

        then:
        response.totalCount == vehicleIds.size()
        next.totalCount == response.totalCount
        next.totalPages == response.totalPages
        next.items.size() == response.totalCount - 5
        next.items.each {
            assert !firstPageIds.contains(it.id)
            assert vehicleIds.contains(it.id)
        }

        when:
        Page<Vehicle> nextnext = next.next()

        then:
        nextnext.totalCount == response.totalCount
        nextnext.totalPages == response.totalPages
        nextnext.items.size() == 0
    }

    def "I can collect all vehicles"() {
        given:
        List<String> vehicleIds = getVehicleList().subList(0, 10)*.id

        when:
        List<Vehicle> response = connect.vehicles().collect(new VehicleListRequest()
                .withVehicleIds(vehicleIds))

        then:
        response.size() == vehicleIds.size()
        response.each {
            assert vehicleIds.contains(it.id)
        }

        when:
        response = connect.vehicles().collect(new VehicleListRequest()
                .withPageIndex(0)
                .withPageSize(9)
                .withVehicleIds(vehicleIds))

        then:
        response.size() == vehicleIds.size()
        response.each {
            assert vehicleIds.contains(it.id)
        }
    }

    def "I can list a vehicle by name"() {
        given:
        Vehicle vehicle = getVehicleList()[0]

        when:
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest().withName(vehicle.name))

        then:
        response.totalCount == 1
        response.items.size() == 1
        response.items[0].id == vehicle.id
        response.items[0].name == vehicle.name
        response.items[0].registration == vehicle.registration
    }

    def "I can list vehicles by vehicle ids"() {
        given:
        List<Vehicle> vehicles = getVehicleList()
        List<String> vehicleIds = (vehicles*.id as List).subList(0, 2)

        when:
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest()
                .withVehicleIds(vehicleIds))

        then:
        response.totalCount == 2
        response.items.size() == 2
        response.items.each {
            assert vehicleIds.contains(it.id)
        }
    }

    def "I can list vehicles by group ids"() {
        given:
        List<Vehicle> vehicles = getVehicleList()
        List<String> groupIds = ((vehicles*.groupId as Set) as List).subList(0, 2)

        when:
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest()
                .withGroupIds(groupIds))

        then:
        response.totalCount > 0
        response.items.size() > 0
        response.items.each {
            assert groupIds.contains(it.groupId)
        }
    }

    def "I can find the nearest vehicles"() {
        given:
        List<LivePosition> positions = connect.tracking().live()
        LivePosition position = positions.findAll() {it.longitude != null }[1]
        println(ObjectMapperFactory.make().writeValueAsString(positions))
        println("########################################")

        when:
        List<ObjectDistance> response = connect.vehicles().findNearest(new FindNearestRequest()
                .withRadius(2)
                .withMaximumResultsToReturn(10)
                .withLongitude(position.longitude)
                .withLatitude(position.latitude))
        println(ObjectMapperFactory.make().writeValueAsString(response))

        then:
        response.size() > 0
        response.size() <= 10
        response*.id.contains(position.assetId)
    }

    def "I can get the fuel consumption"() {
        when:
        List<VehicleFuelConsumption> response = connect.vehicles().fuelConsumption(new VehicleDateRangeRequest()
                .withStartDate(LocalDate.now().minusMonths(1).withDayOfMonth(1).toDateTimeAtStartOfDay().toLocalDateTime())
                .withEndDate(LocalDate.now().withDayOfMonth(1).toDateTimeAtStartOfDay().toLocalDateTime()))

        then:
        response.size() > 0
        println(ObjectMapperFactory.make().writeValueAsString(response))
    }

    def "I get an error if I use multiple filters"() {
        when:
        Page<Vehicle> response = connect.vehicles().list(new VehicleListRequest()
                .withVehicleIds(Arrays.asList('521665', '668524'))
                .withGroupIds(Arrays.asList('53ccf25cbb50290767c898f1', '53ccf25cbb50290767c898eb')))

        then:
        MasternautServerException e = thrown(MasternautServerException)
        e.error.code == '12004'
        e.error.message == 'Cannot search by more than one parameter, only specify one of: vehicleIds, groupIds, registration or name.'
        e.message == '12004: Cannot search by more than one parameter, only specify one of: vehicleIds, groupIds, registration or name.'
    }

    private List<Vehicle> getVehicleList() {
        vehicleList = vehicleList ?: connect.vehicles().list().items;
    }
}
