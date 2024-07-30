package com.nls.masternaut
//@Ignore
class LocationCategoryIntegrationSpec extends BaseIntegrationSpec {

    def "I can list location categories"() {
        when:
        List<LocationCategory> response = connect.locationCategories().list()

        then:
        response.size() > 0;
        response[0].id != null
        response[0].name != null
        response[0].icon != null
        response[0].locationsCount;
    }
}
