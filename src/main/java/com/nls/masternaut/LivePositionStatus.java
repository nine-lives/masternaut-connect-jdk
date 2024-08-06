package com.nls.masternaut;

public enum LivePositionStatus {
    behaviourHarshBraking,
    behaviourOverSpeed,
    behaviourExcessiveIdling,
    behaviourHarshAcceleration,
    behaviourHarshCornering,
    behaviourEngineOverRevving,
    toggleOff,
    toggleOn,
    stateChange,
    ptoOnIdling,
    ptoOnDriving,
    ptoOff,
    startOfJourney,
    endOfJourney,
    driving,
    idling,
    pingPosition;


    public String getLabel() {
        return Character.toUpperCase(name().charAt(0)) + name().substring(1).replaceAll("([A-Z])", " $1");
    }
}
