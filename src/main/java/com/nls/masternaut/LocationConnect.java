package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

public class LocationConnect {
    private final HttpClient client;

    LocationConnect(HttpClient client) {
        this.client = client;
    }

    public LocationListRequest list() {
        return new LocationListRequest(client);
    }

    public LocationAddRequest add() {
        return new LocationAddRequest(client);
    }

    public LocationUpdateRequest update(String locationId) {
        return new LocationUpdateRequest(client, locationId);
    }

    public void delete(String locationId) {
        client.delete("location/" + locationId, null, Location.class);
    }

    public LocationFindNearestRequest nearest() {
        return new LocationFindNearestRequest(client);
    }
}
