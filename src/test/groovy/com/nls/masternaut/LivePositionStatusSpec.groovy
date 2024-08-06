package com.nls.masternaut

import spock.lang.Specification
import spock.lang.Unroll

class LivePositionStatusSpec extends Specification {
    @Unroll
    def "I can get the label for the  - #status"() {
        when:
        String label = status.label

        then:
        label == expected

        where:
        status | expected
        LivePositionStatus.behaviourHarshBraking | 'Behaviour Harsh Braking'
        LivePositionStatus.behaviourOverSpeed | 'Behaviour Over Speed'
        LivePositionStatus.behaviourExcessiveIdling | 'Behaviour Excessive Idling'
        LivePositionStatus.behaviourHarshAcceleration | 'Behaviour Harsh Acceleration'
        LivePositionStatus.behaviourHarshCornering | 'Behaviour Harsh Cornering'
        LivePositionStatus.behaviourEngineOverRevving | 'Behaviour Engine Over Revving'
        LivePositionStatus.toggleOff | 'Toggle Off'
        LivePositionStatus.toggleOn | 'Toggle On'
        LivePositionStatus.stateChange | 'State Change'
        LivePositionStatus.ptoOnIdling | 'Pto On Idling'
        LivePositionStatus.ptoOnDriving | 'Pto On Driving'
        LivePositionStatus.ptoOff | 'Pto Off'
        LivePositionStatus.startOfJourney | 'Start Of Journey'
        LivePositionStatus.endOfJourney | 'End Of Journey'
        LivePositionStatus.driving | 'Driving'
        LivePositionStatus.idling | 'Idling'
        LivePositionStatus.pingPosition | 'Ping Position'
    }
}
