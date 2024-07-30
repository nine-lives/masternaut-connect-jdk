package com.nls.masternaut

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import spock.lang.Specification

class DriverSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "id": "204642",
                "customerId": "123456",
                "name": "Hayton Wood",
                "active": true,
                "activeDate": "2013-10-24T04:10:00.613",
                "groupId": "5045939e60b13e9f54b1e046",
                "groupName": "Masternaut",
                "defaultVehicleId": "235204",
                "keys": [
                    {
                        "value": "AB7CADE2B1",
                        "type": "RFID",
                        "privateMode": false
                    },
                    {
                        "value": "8999",
                        "type": "DALLAS",
                        "privateMode": false
                    },
                    {
                        "value": "ABCDEABCDEA12A633",
                        "type": "TachoID",
                        "privateMode": true
                    }
                ],
                "tags": ["T1", "T2"],
                "tempVehicleId": "tvid",
                "tempVehicleExpiry": "2024-10-24T04:10:00",
                "tempVehicleNextJourneyOnly": true,
                "tempPrivateMode": true,
                "tempPrivateModeNextJourneyOnly": true
            }
       '''

        when:
        Driver entity = mapper.readValue(payload, Driver)

        then:
        entity.id == '204642'
        entity.customerId == '123456'
        entity.name == 'Hayton Wood'
        entity.active
        entity.activeDate == LocalDateTime.parse("2013-10-24T04:10:00.613")
        entity.groupId == '5045939e60b13e9f54b1e046'
        entity.groupName == 'Masternaut'
        entity.defaultVehicleId == '235204'
        entity.tags == ['T1', 'T2']
        entity.tempVehicleId == 'tvid'
        entity.tempVehicleExpiry == LocalDateTime.parse("2024-10-24T04:10:00")
        entity.tempVehicleNextJourneyOnly
        entity.tempPrivateMode
        entity.tempPrivateModeNextJourneyOnly
        entity.keys.size() == 3
        entity.keys[0].type == DriveKeyType.RFID
        entity.keys[0].value == 'AB7CADE2B1'
        !entity.keys[0].privateMode
        entity.keys[1].type == DriveKeyType.DALLAS
        entity.keys[1].value == '8999'
        !entity.keys[1].privateMode
        entity.keys[2].type == DriveKeyType.TachoID
        entity.keys[2].value == 'ABCDEABCDEA12A633'
        entity.keys[2].privateMode
    }


    def "I can convert a JSON page payload to pages of the entity"() {
        given:
        String payload = '''
            {
                "totalPages":1,
                "totalCount":2,
                "items":[
                    {
                        "id":"3454070",
                        "driverName": "Coburnhill Wood",
                        "groupId": "5315b03360b12054ebd4a8c5",
                        "groupName": "Masternaut",
                        "customerId": "123456",
                        "active": false
                    },
                    {
                        "id" : "3454072",
                        "driverName": "Hayton Wood",
                        "groupId": "5315b03360b12054ebd4a8c5",
                        "groupName": "Masternaut",
                        "customerId": "123456",
                        "active": true,
                        "defaultVehicleId": "235204",
                        "keys": [
                            {
                            "value": "AB7CADE2B1",
                            "type": "RFID",
                            "privateMode": false
                            }
                        ]
                    }
                ]
            }
       '''

        when:
        PageResponse<Driver> entity = mapper.readValue(payload, new TypeReference<PageResponse<Driver>>() {})

        then:
        entity.totalCount == 2
        entity.totalPages == 1
        entity.items.size() == 2
        entity.items[0].id == '3454070'
        entity.items[1].id == '3454072'
        entity.items[1].keys[0].value == 'AB7CADE2B1'
    }
}