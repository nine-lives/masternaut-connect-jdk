package com.nls.masternaut

import com.fasterxml.jackson.databind.ObjectMapper
import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

class LocationSpec extends Specification {
    private ObjectMapper mapper = ObjectMapperFactory.make(true)

    def "I can convert a JSON payload to the entity"() {
        given:
        String payload = '''
            {
                "id": "5b238eac3830eabfb3f82341",
                "name": "Drossaert van Laerstraat",
                "address": {
                    "formattedAddress": "Drossaert van Laerstraat 14, 6181 ED Elsloo, Netherlands",
                    "road": "Drossaert van Laerstraat",
                    "roadNumber": "14",
                    "city": "Elsloo",
                    "postCode": "6181 ED",
                    "country": "Netherlands"
                },
                "coordinate": {
                    "longitude": 5.771913300000051,
                    "latitude": 50.95092470108757
                },
                "customerId": "43643",
                "customerReference": "Not in example ref",
                "categoryId": "559d229ce4b02bebb5efafe7",
                "categoryName": "Customer",
                "polygon": [
                    {
                        "longitude": 5.772627063388086,
                        "latitude": 50.9509247
                    },
                    {
                        "longitude": 5.771913300000051,
                        "latitude": 50.951374360803
                    },
                    {
                        "longitude": 5.771199536612016,
                        "latitude": 50.9509247
                    },
                    {
                        "longitude": 5.771913300000051,
                        "latitude": 50.950475039197
                    }
                ],
                "type": "POLYGON",
                "reference" : "customer ABC111",
                "contact" : "Barry Woodenhouse",
                "email" : "barry.woodenhouse@test.com",
                "phoneNumber" : "+44123456",
                "notes" : "Company in Leeds"
            }
       '''

        when:
        Location entity = mapper.readValue(payload, Location)

        then:
        entity.id == '5b238eac3830eabfb3f82341'
        entity.name == 'Drossaert van Laerstraat'
        entity.address.formattedAddress == 'Drossaert van Laerstraat 14, 6181 ED Elsloo, Netherlands'
        entity.address.roadNumber == '14'
        entity.address.road == 'Drossaert van Laerstraat'
        entity.address.city == 'Elsloo'
        entity.address.postCode == '6181 ED'
        entity.address.country == 'Netherlands'
        entity.coordinate.longitude == 5.771913300000051G
        entity.coordinate.latitude == 50.95092470108757G
        entity.customerId == '43643'
        entity.customerReference == 'Not in example ref'
        entity.categoryId == '559d229ce4b02bebb5efafe7'
        entity.categoryName == 'Customer'
        entity.type == LocationType.POLYGON
        entity.polygon.size() == 4
        entity.polygon[0].longitude == 5.772627063388086G
        entity.polygon[0].latitude == 50.9509247G
        entity.polygon[3].longitude == 5.771913300000051G
        entity.polygon[3].latitude == 50.950475039197G
        entity.reference == 'customer ABC111'
        entity.contact == 'Barry Woodenhouse'
        entity.email == 'barry.woodenhouse@test.com'
        entity.phoneNumber == '+44123456'
        entity.notes == 'Company in Leeds'
    }
}