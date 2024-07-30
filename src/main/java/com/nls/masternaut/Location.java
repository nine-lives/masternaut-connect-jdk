package com.nls.masternaut;

import java.util.List;

public class Location {
    private String id;
    private String name;
    private Address address;
    private Coordinate coordinate;
    private String customerId;
    private String customerReference;
    private String categoryId;
    private String categoryName;
    private LocationType type;
    private List<Coordinate> polygon;
    private String reference;
    private String contact;
    private String phoneNumber;
    private String email;
    private String notes;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LocationType getType() {
        return type;
    }

    public List<Coordinate> getPolygon() {
        return polygon;
    }

    public String getReference() {
        return reference;
    }

    public String getContact() {
        return contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }
}
