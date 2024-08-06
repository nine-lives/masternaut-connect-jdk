package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.List;

public class JourneySummaryRequest extends VehicleListDateRangeRequest<JourneySummaryRequest> {
    @JsonIgnore
    private final transient IClient client;

    public JourneySummaryRequest(IClient client) {
        super(JourneySummaryRequest.class);
        this.client = client;
    }

    public List<DistanceTravelled> fetch() {
        return client.get("tracking/journey/summary", this, new TypeReference<List<DistanceTravelled>>() { });
    }}
