package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

public class TrackingConnect {
    private final HttpClient client;

    public TrackingConnect(HttpClient client) {
        this.client = client;
    }

    public LivePositionRequest live() {
        return new LivePositionRequest(client);
    }

    public LatestLivePositionRequest latest() {
        return new LatestLivePositionRequest(client);
    }

    public JourneySummaryRequest getJourneySummaries() {
        return new JourneySummaryRequest(client);
    }
}
