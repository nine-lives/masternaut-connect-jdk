package com.nls.masternaut;

public interface LatestLivePositionListener {
    void onUpdate(LatestLivePositionList positions);
    void onError(Exception e);
}
