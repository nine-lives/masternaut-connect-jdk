package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

public class DriverConnect {
    private final HttpClient client;

    public DriverConnect(HttpClient client) {
        this.client = client;
    }

    public Driver get(String driverId) {
        return client.get("driver/" + driverId, null, Driver.class);
    }

    public DriverListRequest list() {
        return new DriverListRequest(client);
    }

    public DriverAddRequest add() {
        return new DriverAddRequest(client);
    }

    public DriverUpdateRequest update(String driverId) {
        return new DriverUpdateRequest(client).withId(driverId);
    }

    public void delete(String driverId) {
        client.delete("driver/" + driverId, null, Driver.class);
    }

    public Driver setDefaultVehicle(String driverId, String vehicleId) {
        return client.post("driver/" + driverId + "/vehicle/" + vehicleId, null, Driver.class);
    }

    public Driver unsetDefaultDriver(String driverId, String vehicleId) {
        return client.delete("driver/" + driverId + "/vehicle/" + vehicleId, null, Driver.class);
    }

    public Driver unlinkDefaultDriver(String driverId) {
        return client.delete("driver/" + driverId + "/vehicle", null, Driver.class);
    }

    public DriverFuelConsumptionRequest fuelConsumption() {
        return new DriverFuelConsumptionRequest(client);
    }

    // Not implemented
    /*
    public DriverUtilisationList utilisation(DriverUtilisationRequest request) {
        return client.get("utilisation/driver", request, DriverUtilisationList.class);
    }
    */

    // Not implemented
    /*
    public DriverJourneysList journeys(DriverJourneysRequest request) {
        return client.get("journey/detail/driver", request, DriverJourneysRequest.class);
    }
    */

    // Not implemented
    /*
    public DriverJourneysLatestList journeys(DriverJourneysLatestRequest request) {
        return client.get("journey/detail/driver/latest", request, DriverJourneysLatestList.class);
    }
    */

    // Not implemented
    /*
    public Page<TrackingEvent> journeys(DriverJourneysHistoryRequest request) {
            return new Page<TrackingEvent>(
                client.get("tracking/history/driver", request, new TypeReference<PageResponse<TrackingEvent>>() {}),
                () -> journeys(request.next()));
    }
    */

    // Not implemented
    /*
    public DriverJourneyHistoryLatestList journeys(DriverJourneyHistoryLatestRequest request) {
            return new Page<TrackingEvent>(
                client.get("tracking/history/driver/latest", request, DriverJourneyHistoryLatestList.class),
                () -> journeys(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<DriverInputUsage> inputUsage(DriverInputUsageRequest request) {
            return new Page<TrackingEvent>(
                client.get("tracking/history/vehicle", request, new TypeReference<PageResponse<DriverInputUsage>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<DriverSpeedingDetail> speeding(DriverSpeedingDetailRequest request) {
            return new Page<DriverSpeedingDetail>(
                client.get("speeding/detail/driver", request, new TypeReference<PageResponse<DriverSpeedingDetail>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<DriverIdlingDetail> idling(DriverSpeedingDetailRequest request) {
            return new Page<DriverIdlingDetail>(
                client.get("idling/detail/driver", request, new TypeReference<PageResponse<DriverIdlingDetail>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<DriverBehavior> behaviour(DriverBehaviourDetailRequest request) {
            return new Page<DriverBehavior>(
                client.get("driverbehaviourmetrics/driver/summary", request, new TypeReference<PageResponse<DriverBehavior>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public List<DriverBehaviorMetric> behaviour(DriverBehaviourMetricRequest request) {
        client.post("driverbehaviourmetrics", request, new TypeReference<List<DriverBehavior>>() {}));
    }
    */

}
