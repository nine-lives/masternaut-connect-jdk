package com.nls.masternaut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class VehicleConnect {
    private final HttpClient client;

    public VehicleConnect(HttpClient client) {
        this.client = client;
    }

    public Page<Vehicle> list() {
        return list(new VehicleListRequest());
    }

    public Page<Vehicle> list(VehicleListRequest request) {
        return new Page<Vehicle>(
            client.get("vehicle", request, new TypeReference<PageResponse<Vehicle>>() { }),
            () -> list(request.next()));
    }

    public List<Vehicle> collect() {
        return collect(new VehicleListRequest());
    }

    public List<Vehicle> collect(VehicleListRequest request) {
        if (request.getPageSize() == null) {
            request.withPageSize(200);
        }

        List<Vehicle> result = new ArrayList<>();
        Page<Vehicle> page = list(request.withPageIndex(0));
        do {
            result.addAll(page.getItems());
            page = page.next();
        } while (page.hasNext());

        return result;
    }

    public List<ObjectDistance> findNearest(FindNearestRequest request) {
        return client.get("vehicle/nearest", request, new TypeReference<List<ObjectDistance>>() { });
    }

    public List<VehicleFuelConsumption> fuelConsumption(VehicleDateRangeRequest request) {
        return client.get("fuel/vehicle", request, new TypeReference<List<VehicleFuelConsumption>>() { });
    }

    public Vehicle update(String vehicleId, VehicleUpdateRequest request) {
        return client.put("vehicle/" + vehicleId, request, Vehicle.class);
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
