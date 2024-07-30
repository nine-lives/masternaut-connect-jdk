package com.nls.masternaut;

public class MasternautServerException extends MasternautException {
    private final int statusCode;
    private final String statusMessage;
    private final MasternautError error;

    public MasternautServerException(int statusCode, String statusMessage, MasternautError error) {
        super(buildMessage(statusCode, statusMessage, error));
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.error = error;
    }

    /**
     * Get the HTTP status code
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the HTTP status message
     *
     * @return the status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Get the error returned by the server
     *
     * @return the list of errors
     */
    public MasternautError getError() {
        return error;
    }

    private static String buildMessage(int statusCode, String statusMessage, MasternautError error) {
        return error == null
                ? String.format("%d: %s", statusCode, statusMessage)
                : String.format("%s: %s", error.getCode(), error.getMessage());
    }
}
