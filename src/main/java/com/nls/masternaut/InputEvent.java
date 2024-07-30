package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.util.List;

public class InputEvent {
    private LocalDateTime eventDateTime;
    private String eventKey;
    private String eventLabel;
    private List<Channel> channels;

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public String getEventKey() {
        return eventKey;
    }

    public String getEventLabel() {
        return eventLabel;
    }

    public List<Channel> getChannels() {
        return channels;
    }
}
