package com.nls.masternaut;

import com.nls.masternaut.client.IClient;

public class DriverListRequest extends PageRequest<DriverListRequest, Driver> {
    DriverListRequest(IClient client) {
        super(client, DriverListRequest.class);
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
