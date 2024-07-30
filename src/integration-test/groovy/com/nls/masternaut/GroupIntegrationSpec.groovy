package com.nls.masternaut

import spock.lang.Ignore

//@Ignore
class GroupIntegrationSpec extends BaseIntegrationSpec {

    def "I can list groups"() {
        when:
        List<Group> response = connect.groups().list()

        then:
        response.size() > 0;
        response[0].id != null
        response[0].name != null
        response[0].countDrivers != null
        response[0].countVehicles != null;
        response[0].path != null;
    }
}
