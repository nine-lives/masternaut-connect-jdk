package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nls.masternaut.client.IClient;

public class DriverAddRequest extends DriverCommitRequest<DriverAddRequest> {
    @JsonIgnore
    private final transient IClient client;

    public DriverAddRequest(IClient client) {
        super(DriverAddRequest.class);
        this.client = client;
    }

    public Driver commit() {
        return client.post("driver", this, Driver.class);
    }
}
