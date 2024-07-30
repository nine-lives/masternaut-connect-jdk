package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.function.Function;

public class LatestLivePositionList {
    private int totalCount;
    private LocalDateTime processedDateTime;
    private List<LivePosition> items;

    private transient Function<LocalDateTime, LatestLivePositionList> refresh;

    public int getTotalCount() {
        return totalCount;
    }

    public LocalDateTime getProcessedDateTime() {
        return processedDateTime;
    }

    public List<LivePosition> getItems() {
        return items;
    }

    public LatestLivePositionList refresh() {
        return refresh.apply(processedDateTime);
    }

    LatestLivePositionList withRefresh(Function<LocalDateTime, LatestLivePositionList> refresh) {
        this.refresh = refresh;
        return this;
    }
}
