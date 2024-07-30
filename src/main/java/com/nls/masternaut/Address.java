package com.nls.masternaut;

public class Address {
    private String formattedAddress;
    private String roadNumber;
    private String road;
    private String city;
    private String postCode;
    private String country;

    public Address() {

    }

    public Address(Address copy) {
        this.roadNumber = copy.roadNumber;
        this.road = copy.road;
        this.city = copy.city;
        this.postCode = copy.postCode;
        this.country = copy.country;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getRoadNumber() {
        return roadNumber;
    }

    public Address withRoadNumber(String roadNumber) {
        this.roadNumber = roadNumber;
        return this;
    }

    public String getRoad() {
        return road;
    }

    public Address withRoad(String road) {
        this.road = road;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address withCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public Address withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address withCountry(String country) {
        this.country = country;
        return this;
    }
}
