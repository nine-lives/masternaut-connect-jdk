package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public abstract class LocationCommitRequest<T extends LocationCommitRequest<T>> {
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

    @JsonIgnore
    private final transient Class<T> clazz;

    protected LocationCommitRequest(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public T withName(String name) {
        this.name = name;
        return clazz.cast(this);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public T withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return clazz.cast(this);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public T withCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return clazz.cast(this);
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public T withRadius(BigDecimal radius) {
        this.radius = radius;
        return clazz.cast(this);
    }

    public Address getAddress() {
        return address;
    }

    public T withAddress(Address address) {
        this.address = address;
        return clazz.cast(this);
    }

    public String getReference() {
        return reference;
    }

    public T withReference(String reference) {
        this.reference = reference;
        return clazz.cast(this);
    }

    public String getContact() {
        return contact;
    }

    public T withContact(String contact) {
        this.contact = contact;
        return clazz.cast(this);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public T withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return clazz.cast(this);
    }

    public String getEmail() {
        return email;
    }

    public T withEmail(String email) {
        this.email = email;
        return clazz.cast(this);
    }

    public String getNotes() {
        return notes;
    }

    public T withNotes(String notes) {
        this.notes = notes;
        return clazz.cast(this);
    }
}
