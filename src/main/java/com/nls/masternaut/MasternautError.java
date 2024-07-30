package com.nls.masternaut;

public final class MasternautError {

    private String errorCode;
    private String errorMessage;

    public MasternautError() {
    }

    public MasternautError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMessage;
    }
}
