package com.nls.masternaut

import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDate

//@Ignore
class DriverIntegrationSpec extends BaseIntegrationSpec {
    private List<Driver> driverList

    def "I can list drivers"() {
        when:
        Page<Driver> response = connect.drivers().list()

        then:
        response.totalCount > 0;
        response.totalPages == null;
        response.items.size() > 0;
        response.items[0].id != null
        response.items[0].name != null

        response.items.each { println(it.id + ' ' + it.name + ' ' + it.groupId)}

        cleanup:
        driverList = response?.items
    }

    def "I can get a driver"() {
        given:
        Driver driver = getDriverList()[0];

        when:
        Driver response = connect.drivers().get(driver.id);

        then:
        response.id == driver.id;
        response.name == driver.name;
    }

    def "I can page drivers"() {
        when:
        Page<Driver> response = connect.drivers().list(new DriverListRequest()
            .withPageIndex(0)
            .withPageSize(3))

        then:
        response.totalCount > 3;
        response.totalPages == (int) (response.totalCount / 3) + (response.totalCount % 3 ? 1 : 0);
        response.items.size() == 3;

        when:
        Page<Driver> response1 = connect.drivers().list(new DriverListRequest()
                .withPageIndex(0)
                .withPageSize(2))

        then:
        response1.totalCount == response.totalCount;
        response1.totalPages == (int) (response.totalCount / 2) + (response.totalCount % 2 ? 1 : 0);
        response1.items.size() == 2;
        response1.items[0].id == response.items[0].id
        response1.items[1].id == response.items[1].id

        when:
        Page<Driver> response2 = connect.drivers().list(new DriverListRequest()
                .withPageIndex(1)
                .withPageSize(2))

        then:
        response2.totalCount == response.totalCount;
        response2.totalPages == (int) (response.totalCount / 2) + (response.totalCount % 2 ? 1 : 0);
        response2.items.size() == 2;
        response2.items[0].id == response.items[2].id

        when:
        Page<Driver> response3 = connect.drivers().list(new DriverListRequest()
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
        Page<Driver> response4 = connect.drivers().list(new DriverListRequest()
                .withPageIndex(0)
                .withPageSize(5))

        then:
        response4.totalCount == response.totalCount;
        response4.totalPages == (int) (response.totalCount / 5) + (response.totalCount % 5 ? 1 : 0);
        response4.items.size() == 5;
    }

    def "I can get next drivers"() {
        given:
        Page<Driver> response = connect.drivers().list(new DriverListRequest()
                .withPageIndex(0)
                .withPageSize(5))
        Set<String> firstPageIds = response.items*.id as Set

        when:
        Page<Driver> next = response.next()

        then:
        next.totalCount == response.totalCount
        next.totalPages == response.totalPages
        next.items.size() > 0
        next.items.each {
            assert !firstPageIds.contains(it.id)
        }
    }

    def "I can get next Drivers with filter"() {
        given:
        List<String> driverIds = getDriverList().subList(0, 4)*.id
        Page<Driver> response = connect.drivers().list(new DriverListRequest()
                .withPageIndex(0)
                .withPageSize(2))
        Set<String> firstPageIds = response.items*.id as Set

        when:
        Page<Driver> next = response.next()

        then:
        next.totalCount == response.totalCount
        next.totalPages == response.totalPages
        next.items.size() == 2
        next.items.each {
            assert !firstPageIds.contains(it.id)
            assert driverIds.contains(it.id)
        }
    }

    def "I can collect all drivers"() {
        given:
        int totalCount = connect.drivers().list(new DriverListRequest().withPageIndex(0).withPageSize(1)).totalCount

        when:
        List<Driver> response = connect.drivers().collect()

        then:
        response.size() == totalCount
    }

    def "I can get the fuel consumption"() {
        when:
        List<DriverFuelConsumption> response = connect.drivers().fuelConsumption(new DriverDateRangeRequest()
                .withStartDate(LocalDate.now().minusMonths(1).withDayOfMonth(1).toDateTimeAtStartOfDay().toLocalDateTime())
                .withEndDate(LocalDate.now().withDayOfMonth(1).toDateTimeAtStartOfDay().toLocalDateTime()))

        then:
        response.size() > 0
        println(ObjectMapperFactory.make().writeValueAsString(response))
    }

    private List<Driver> getDriverList() {
        driverList = driverList ?: connect.drivers().list().items;
    }

}
