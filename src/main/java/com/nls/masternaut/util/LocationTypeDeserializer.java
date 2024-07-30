package com.nls.masternaut.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.nls.masternaut.LocationType;

import java.io.IOException;

public class LocationTypeDeserializer extends StdScalarDeserializer<LocationType> {
    LocationTypeDeserializer() {
        super(LocationType.class);
    }

    @Override
    public LocationType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken().id() == JsonTokenId.ID_STRING) {
            String text = p.getText().trim();
            if (_isEmptyOrTextualNull(text)) {
                return getNullValue(ctxt);
            }

            try {
                return LocationType.valueOf(text);
            } catch (IllegalArgumentException iae) {
                return (LocationType) ctxt.handleWeirdStringValue(_valueClass, text, "not a valid location type");
            }
        }

        return (LocationType) ctxt.handleUnexpectedToken(_valueClass, p);
    }
}
