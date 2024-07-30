package com.nls.masternaut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class DriverConnect {
    private final HttpClient client;

    public DriverConnect(HttpClient client) {
        this.client = client;
    }

    public Driver get(String driverId) {
        return client.get("driver/" + driverId, null, Driver.class);
    }

    public Page<Driver> list() {
        return list(new DriverListRequest());
    }

    public Page<Driver> list(DriverListRequest request) {
        return new Page<Driver>(
            client.get("driver", request, new TypeReference<PageResponse<Driver>>() { }),
            () -> list(request.next()));
    }

    public List<Driver> collect() {
        return collect(new DriverListRequest());
    }

    private List<Driver> collect(DriverListRequest request) {
        if (request.getPageSize() == null) {
            request.withPageSize(200);
        }

        List<Driver> result = new ArrayList<>();
        Page<Driver> page = list(request.withPageIndex(0));
        do {
            result.addAll(page.getItems());
            page = page.next();
        } while (page.hasNext());

        return result;
    }

    public Driver add(DriverUpdateRequest request) {
        return client.post("driver", request.withId(null), Driver.class);
    }

    public Driver update(String driverId, DriverUpdateRequest request) {
        return client.put("driver/" + driverId, request.withId(driverId), Driver.class);
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

    public List<DriverFuelConsumption> fuelConsumption(DriverDateRangeRequest request) {
        return client.get("fuel/driver", request, new TypeReference<List<DriverFuelConsumption>>() { });
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
