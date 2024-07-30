package com.nls.masternaut


import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import org.joda.time.LocalDateTime
import spock.lang.Specification

class LivePositionSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
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
       '''

        when:
        LivePosition entity = mapper.readValue(payload, LivePosition)

        then:
        entity.assetId == '54072'
        entity.assetRegistration == 'YA99RTE'
        entity.assetName == 'YA99RTE'
        entity.driverName == 'Coburnhill Wood'
        entity.driverId == '3454070'
        entity.driverGroupId == '5315b03360b12054ebd4a8c5'
        entity.driverGroupName == 'Masternaut'
        entity.assetGroupId == '5315b03360b12054ebd4a8c5'
        entity.assetGroupName == 'LeedsFleet'
        entity.date == LocalDateTime.parse('2014-02-06T14:29:00.000')
        entity.status == LivePositionStatus.driving
        entity.eventType == EventType.driving
        entity.speed == 40G
        entity.heading == 0G
        entity.latitude == 53.8304G
        entity.longitude == -1.3421G
        entity.roadNumber == '48'
        entity.road == 'Priory Park'
        entity.city == 'Leeds'
        entity.postcode == 'LS25 3DF'
        entity.country == 'England'
        entity.formattedAddress == 'Priory Park, Leeds, LS25 3DF'
        entity.locationName == 'Masternaut'
        entity.locationId == '506b072b60b1f908e7b59cef'
        entity.inputs.size() == 1
        entity.inputs[0].inputId == '27519.6653192'
        entity.inputs[0].inputLabel == 'PTO'
        entity.inputs[0].inputState == 'Off'
        entity.inputs[0].customInputLabel == 'Crane'
        entity.inputs[0].customInputState == 'not operating'
        entity.inputEvents.size() == 1
        entity.inputEvents[0].eventDateTime == LocalDateTime.parse('2018-11-27T11:01:03.003')
        entity.inputEvents[0].eventKey == 'refuse'
        entity.inputEvents[0].eventLabel == 'Refuse Collection'
        entity.inputEvents[0].channels.size() == 2
        entity.inputEvents[0].channels[0].label == 'Bin Weight'
        entity.inputEvents[0].channels[0].unit == 'kg'
        entity.inputEvents[0].channels[0].value == '10.8'
        entity.inputEvents[0].channels[0].stringValue == '10.8'
        entity.inputEvents[0].channels[0].order == 10
        entity.inputEvents[0].channels[0].key == 'binWeight'
        entity.assetType == VehicleType.VAN
        entity.tags == ['PTO', 'RM']
        !entity.privacy
        entity.odometer == 239196.4G
        entity.energyType == 'EV'
        entity.fuelType == [FuelType.ELECTRIC]
        entity.electricBatteryLevelPercent == 45.0G
        !entity.electricBatteryLevelPercentEstimated
        entity.electricRangeKm == 23.5G
        entity.electricRangeKmEstimated
        entity.electricCharging
        entity.electricChargingTimeRemainingMins == 45.25G
        entity.engineRpm == 6012
        entity.fuelLevelPercentage == 32.5G
        entity.fuelLevelLitres == 24.6G
        entity.engineTotalHours == 3046.05G
        entity.engineTotalHoursType == EngineTotalHoursType.ESTIMATED
    }
}