package com.nls.masternaut;

public class MasternautException extends RuntimeException {

    public MasternautException(String message) {
        super(message);
    }

    public MasternautException(Throwable e) {
        super(e);
    }
}
