package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

public class LocationUpdateRequest extends LocationCommitRequest<LocationUpdateRequest> {
    @JsonIgnore
    private final transient IClient client;

    private final String id;

    public LocationUpdateRequest(IClient client, String locationId) {
        super(LocationUpdateRequest.class);
        this.client = client;
        this.id = locationId;
    }

    public LocationUpdateRequest withLocation(Location copy) {
        return withName(copy.getName())
                .withCategoryName(copy.getCategoryName())
        //this.radius = copy.getRadius();
                .withAddress(copy.getAddress() == null ? null : new Address(copy.getAddress()))
                .withCoordinate(copy.getCoordinate() == null ? null : new Coordinate(copy.getCoordinate()))
                .withReference(copy.getReference())
                .withContact(copy.getContact())
                .withPhoneNumber(copy.getPhoneNumber())
                .withEmail(copy.getEmail())
                .withNotes(copy.getNotes());
    }

    String getId() {
        return id;
    }

    public Location commit() {
        return client.put("location", this, Location.class);
    }
}
