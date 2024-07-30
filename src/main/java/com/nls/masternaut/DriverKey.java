package com.nls.masternaut;

public class DriverKey {
    private DriveKeyType type;
    private String value;
    private Boolean privateMode;

    public DriverKey() {

    }

    public DriverKey(DriverKey copy) {
        this.type = copy.type;
        this.value = copy.value;
        this.privateMode = copy.privateMode;
    }

    public DriveKeyType getType() {
        return type;
    }

    public DriverKey withType(DriveKeyType type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DriverKey withValue(String value) {
        this.value = value;
        return this;
    }

    public Boolean getPrivateMode() {
        return privateMode;
    }

    public DriverKey withPrivateMode(Boolean privateMode) {
        this.privateMode = privateMode;
        return this;
    }
}
