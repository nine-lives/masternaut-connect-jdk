package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

public class DriverListRequest extends PageRequest<DriverListRequest, Driver> {
    DriverListRequest(IClient client) {
        super(client, DriverListRequest.class, new TypeReference<PageResponse<Driver>>() { });
    }

    @Override
    public int getMaxPageSize() {
        return 200;
    }

    @Override
    protected String getPath() {
        return "driver";
    }
}
