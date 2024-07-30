package com.nls.masternaut


import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDateTime
import spock.lang.Specification

class LatestLivePositionListSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
            "totalCount": 1,
            "processedDateTime": "2017-04-20T08:00:21.226",
            "items": [
                {
                    "assetId": "54072",
                    "assetRegistration": "YA99RTE",
                    "assetName": "YA99RTE",
                    "driverId": "3454070",
                    "driverName": "Coburnhill Wood",
                    "driverGroupId": "5315b03360b12054ebd4a8c5",
                    "driverGroupName": "Masternaut",
                    "assetGroupId": "5315b03360b12054ebd4a8c5",
                    "assetGroupName": "LeedsFleet",
                    "date": "2014-02-06T14:29:00.000",
                    "status": "driving",
                    "eventType": "driving",
                    "speed": 40,
                    "heading": 0,
                    "latitude": 53.8304,
                    "longitude": -1.3421,
                    "roadNumber": "48",
                    "road": "Priory Park",
                    "city": "Leeds",
                    "postcode": "LS25 3DF",
                    "country": "England",
                    "formattedAddress": "Priory Park, Leeds, LS25 3DF",
                    "locationName": "Masternaut",
                    "locationGroupName": "Sales Home",
                    "locationId": "506b072b60b1f908e7b59cef",
                    "inputs": [
                        {
                            "inputId": "27519.6653192",
                            "inputLabel": "PTO",
                            "inputState": "Off",
                            "customInputLabel": "Crane",
                            "customInputState": "not operating"
                        }
                    ],
                    "inputEvents": [
                        {
                            "eventDateTime": "2018-11-27T11:01:03.003",
                            "eventKey": "refuse",
                            "eventLabel": "Refuse Collection",
                            "channels": [
                                {
                                "label": "Bin Weight",
                                "unit": "kg",
                                "value": 10.8,
                                "stringValue": "10.8",
                                "order": 10,
                                "key": "binWeight"
                                },
                                {
                                "label": "Bin RFID Tag",
                                "value": "123EDE",
                                "stringValue": "123EDE",
                                "order": 11,
                                "key": "binRFID"
                                }
                            ]
                        }
                    ],
                    "assetType": "VAN",
                    "tags": ["PTO","RM"],
                    "privacy": false,
                    "energyType": "EV",
                    "fuelType": ["ELECTRIC"],
                    "electricBatteryLevelPercent": 45.0,
                    "electricBatteryLevelPercentEstimated": false,
                    "electricRangeKm": 23.5,
                    "electricRangeKmEstimated": true,
                    "electricCharging": true,
                    "electricChargingTimeRemainingMins": 45.25,
                    "engineRpm": 6012.1,
                    "fuelLevelPercentage": 32.5,
                    "fuelLevelLitres": 24.6,
                    "odometer": 239196.4,
                    "engineTotalHours": 3046.05,
                    "engineTotalHoursType": "ESTIMATED"
                }
            ]
        }
       '''

        when:
        LatestLivePositionList entity = mapper.readValue(payload, LatestLivePositionList)

        then:
        entity.totalCount == 1
        entity.processedDateTime == LocalDateTime.parse('2017-04-20T08:00:21.226')
        entity.items[0].assetId == '54072'
        entity.items[0].assetRegistration == 'YA99RTE'
        entity.items[0].assetName == 'YA99RTE'
        entity.items[0].driverName == 'Coburnhill Wood'
        entity.items[0].driverId == '3454070'
        entity.items[0].driverGroupId == '5315b03360b12054ebd4a8c5'
        entity.items[0].driverGroupName == 'Masternaut'
        entity.items[0].assetGroupId == '5315b03360b12054ebd4a8c5'
        entity.items[0].assetGroupName == 'LeedsFleet'
        entity.items[0].date == LocalDateTime.parse('2014-02-06T14:29:00.000')
        entity.items[0].status == LivePositionStatus.driving
        entity.items[0].eventType == EventType.driving
        entity.items[0].speed == 40G
        entity.items[0].heading == 0G
        entity.items[0].latitude == 53.8304G
        entity.items[0].longitude == -1.3421G
        entity.items[0].roadNumber == '48'
        entity.items[0].road == 'Priory Park'
        entity.items[0].city == 'Leeds'
        entity.items[0].postcode == 'LS25 3DF'
        entity.items[0].country == 'England'
        entity.items[0].formattedAddress == 'Priory Park, Leeds, LS25 3DF'
        entity.items[0].locationName == 'Masternaut'
        entity.items[0].locationId == '506b072b60b1f908e7b59cef'
        entity.items[0].inputs.size() == 1
        entity.items[0].inputs[0].inputId == '27519.6653192'
        entity.items[0].inputs[0].inputLabel == 'PTO'
        entity.items[0].inputs[0].inputState == 'Off'
        entity.items[0].inputs[0].customInputLabel == 'Crane'
        entity.items[0].inputs[0].customInputState == 'not operating'
        entity.items[0].inputEvents.size() == 1
        entity.items[0].inputEvents[0].eventDateTime == LocalDateTime.parse('2018-11-27T11:01:03.003')
        entity.items[0].inputEvents[0].eventKey == 'refuse'
        entity.items[0].inputEvents[0].eventLabel == 'Refuse Collection'
        entity.items[0].inputEvents[0].channels.size() == 2
        entity.items[0].inputEvents[0].channels[0].label == 'Bin Weight'
        entity.items[0].inputEvents[0].channels[0].unit == 'kg'
        entity.items[0].inputEvents[0].channels[0].value == '10.8'
        entity.items[0].inputEvents[0].channels[0].stringValue == '10.8'
        entity.items[0].inputEvents[0].channels[0].order == 10
        entity.items[0].inputEvents[0].channels[0].key == 'binWeight'
        entity.items[0].assetType == VehicleType.VAN
        entity.items[0].tags == ['PTO', 'RM']
        !entity.items[0].privacy
        entity.items[0].odometer == 239196.4G
        entity.items[0].energyType == 'EV'
        entity.items[0].fuelType == [FuelType.ELECTRIC]
        entity.items[0].electricBatteryLevelPercent == 45.0G
        !entity.items[0].electricBatteryLevelPercentEstimated
        entity.items[0].electricRangeKm == 23.5G
        entity.items[0].electricRangeKmEstimated
        entity.items[0].electricCharging
        entity.items[0].electricChargingTimeRemainingMins == 45.25G
        entity.items[0].engineRpm == 6012
        entity.items[0].fuelLevelPercentage == 32.5G
        entity.items[0].fuelLevelLitres == 24.6G
        entity.items[0].engineTotalHours == 3046.05G
        entity.items[0].engineTotalHoursType == EngineTotalHoursType.ESTIMATED
    }
}