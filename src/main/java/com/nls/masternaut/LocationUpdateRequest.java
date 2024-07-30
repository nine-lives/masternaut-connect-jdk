package com.nls.masternaut;

import java.math.BigDecimal;

public class LocationUpdateRequest {
    private String id;
    private String name;
    private String categoryName;
    private BigDecimal radius;
    private Coordinate coordinate;
    private Address address;
    private String reference;
    private String contact;
    private String phoneNumber;
    private String email;
    private String notes;

    public LocationUpdateRequest() {

    }

    public LocationUpdateRequest(Location copy) {
        this.name = copy.getName();
        this.categoryName = copy.getCategoryName();
        //this.radius = copy.getRadius();
        this.address = copy.getAddress() == null ? null : new Address(copy.getAddress());
        this.coordinate = copy.getCoordinate() == null ? null : new Coordinate(copy.getCoordinate());
        this.reference = copy.getReference();
        this.contact = copy.getContact();
        this.phoneNumber = copy.getPhoneNumber();
        this.email = copy.getEmail();
        this.notes = copy.getNotes();
    }

    String getId() {
        return id;
    }

    LocationUpdateRequest withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LocationUpdateRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LocationUpdateRequest withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public LocationUpdateRequest withCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public LocationUpdateRequest withRadius(BigDecimal radius) {
        this.radius = radius;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public LocationUpdateRequest withAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public LocationUpdateRequest withReference(String reference) {
        this.reference = reference;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public LocationUpdateRequest withContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocationUpdateRequest withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LocationUpdateRequest withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public LocationUpdateRequest withNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
