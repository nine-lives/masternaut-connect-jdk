package com.nls.masternaut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class LocationConnect {
    private final HttpClient client;

    LocationConnect(HttpClient client) {
        this.client = client;
    }

    public Page<Location> list() {
        return client.get("location/current", null, new TypeReference<Page<Location>>() { });
    }

    public Page<Location> list(LocationListRequest request) {
        return new Page<Location>(
            client.get("location/current", request, new TypeReference<PageResponse<Location>>() { }),
            () -> list(request.next()));
    }

    public List<Location> collect() {
        return collect(new LocationListRequest());
    }

    public List<Location> collect(LocationListRequest request) {
        if (request.getPageSize() == null) {
            request.withPageSize(50);
        }

        List<Location> result = new ArrayList<>();
        Page<Location> page = list(request.withPageIndex(0));
        do {
            result.addAll(page.getItems());
            page = page.next();
        } while (page.hasNext());

        return result;
    }

    public Location add(LocationUpdateRequest request) {
        return client.post("location", request.withId(null), Location.class);
    }

    public Location update(String locationId, LocationUpdateRequest request) {
        return client.put("location", request.withId(locationId), Location.class);
    }

    public void delete(String locationId) {
        client.delete("location/" + locationId, null, Location.class);
    }

    public List<ObjectDistance> findNearest(FindNearestRequest request) {
        return client.get("location/nearest", request, new TypeReference<List<ObjectDistance>>() { });
    }

}
