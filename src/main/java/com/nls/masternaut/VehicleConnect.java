package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

public class VehicleConnect {
    private final HttpClient client;

    public VehicleConnect(HttpClient client) {
        this.client = client;
    }

    public VehicleListRequest list() {
        return new VehicleListRequest(client);
    }

    public VehicleFindNearestRequest nearest() {
        return new VehicleFindNearestRequest(client);
    }

    public VehicleFuelConsumptionRequest fuelConsumption() {
        return new VehicleFuelConsumptionRequest(client);
    }

    public VehicleUpdateRequest update(String vehicleId) {
        return new VehicleUpdateRequest(client, vehicleId);
    }

    // Not implemented
    /*
    public VehicleUtilisationList utilisation(VehicleUtilisationRequest request) {
        return client.get("utilisation/vehicle", request, VehicleUtilisationList.class);
    }
    */

    // Not implemented
    /*
    public VehicleJourneysList journeys(VehicleJourneysRequest request) {
        return client.get("journey/detail/vehicle", request, VehicleJourneysList.class);
    }
    */

    // Not implemented
    /*
    public VehicleJourneysLatestList journeys(VehicleJourneysLatestRequest request) {
        return client.get("journey/detail/vehicle/latest", request, VehicleJourneysLatestList.class);
    }
    */

    // Not implemented
    /*
    public Page<TrackingEvent> journeys(VehicleJourneysHistoryRequest request) {
            return new Page<TrackingEvent>(
                client.get("tracking/history/vehicle", request, new TypeReference<PageResponse<TrackingEvent>>() {}),
                () -> journeys(request.next()));
    }
    */

    // Not implemented
    /*
    public VehicleJourneyHistoryLatestList journeys(VehicleJourneyHistoryLatestRequest request) {
            return new Page<TrackingEvent>(
                client.get("tracking/history/vehicle/latest", request, VehicleJourneyHistoryLatestList.class),
                () -> journeys(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<VehicleInputUsage> inputUsage(VehicleInputUsageRequest request) {
            return new Page<TrackingEvent>(
                client.get("tracking/history/vehicle", request, new TypeReference<PageResponse<VehicleInputUsage>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<VehicleSpeedingDetail> speeding(VehicleSpeedingDetailRequest request) {
            return new Page<VehicleSpeedingDetail>(
                client.get("speeding/detail/vehicle", request, new TypeReference<PageResponse<VehicleSpeedingDetail>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<VehicleIdlingDetail> idling(VehicleIdlingDetailRequest request) {
            return new Page<VehicleIdlingDetail>(
                client.get("idling/detail/vehicle", request, new TypeReference<PageResponse<VehicleIdlingDetail>>() {}),
                () -> inputUsage(request.next()));
    }
    */

    // Not implemented
    /*
    public Page<VehicleDriverBehavior> driverBehaviour(DriverBehaviourDetailRequest request) {
            return new Page<VehicleDriverBehavior>(
                client.get("driverbehaviourmetrics/vehicle/summary", request, new TypeReference<PageResponse<VehicleDriverBehavior>>() {}),
                () -> inputUsage(request.next()));
    }
    */

}
