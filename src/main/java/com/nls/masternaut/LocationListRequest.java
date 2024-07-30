package com.nls.masternaut;

public class LocationListRequest extends PageRequest<LocationListRequest> {

    private String name;
    private String address;
    private String category;
    private String reference;
    private String phoneNumber;

    public LocationListRequest() {
        super(LocationListRequest.class);
    }

    public String getName() {
        return name;
    }

    public LocationListRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LocationListRequest withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public LocationListRequest withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public LocationListRequest withReference(String reference) {
        this.reference = reference;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocationListRequest withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
