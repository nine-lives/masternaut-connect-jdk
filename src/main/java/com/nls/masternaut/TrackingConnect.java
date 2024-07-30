package com.nls.masternaut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.HttpClient;
import org.joda.time.LocalDateTime;

import java.util.List;

public class TrackingConnect {
    private final HttpClient client;

    public TrackingConnect(HttpClient client) {
        this.client = client;
    }

    public List<LivePosition> live() {
        return live(new LivePositionRequest());
    }

    public List<LivePosition> live(LivePositionRequest request) {
        return client.get("tracking/live", request, new TypeReference<List<LivePosition>>() { });
    }

    public LatestLivePositionList latest() {
        return latest(new LivePositionRequest(), null);
    }

    public LatestLivePositionList latest(LocalDateTime fromDateTime) {
        return latest(new LivePositionRequest(), fromDateTime);
    }

    public LatestLivePositionList latest(LivePositionRequest request) {
        return latest(new LivePositionRequest(), null);
    }

    public LatestLivePositionList latest(LivePositionRequest request, LocalDateTime fromDateTime) {
        LivePositionRequest clone = request.copy();
        return client.get("tracking/live/latest", request.withFromDateTime(fromDateTime), LatestLivePositionList.class)
                .withRefresh((from) -> latest(clone, from));
    }

    public List<DistanceTravelled> getJourneySummaries(VehicleListDateRangeRequest request) {
        return client.get("tracking/journey/summary", request, new TypeReference<List<DistanceTravelled>>() { });
    }
}
