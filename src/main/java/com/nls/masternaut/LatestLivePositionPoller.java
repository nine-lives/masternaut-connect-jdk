package com.nls.masternaut;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class LatestLivePositionPoller {
    private final LatestLivePositionRequest request;
    private final TimeUnit unit;
    private final Long period;
    private final boolean fixedRate;
    private final ScheduledExecutorService executorService;
    private final List<LatestLivePositionListener> listeners = Collections.synchronizedList(new ArrayList<>());
    private ScheduledFuture<?> scheduler;
    private LocalDateTime fromDateTime;

    private LatestLivePositionPoller(Builder builder) {
        this.request = builder.request;
        this.fromDateTime = builder.request.getFromDateTime();
        this.unit = builder.unit;
        this.period = builder.period;
        this.fixedRate = builder.fixedRate;
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public static Builder builder(LatestLivePositionRequest request) {
        return new Builder(request);
    }

    public void addListener(LatestLivePositionListener listener) {
        synchronized (listeners) {
            this.listeners.add(listener);
        }
    }

    public void start(long delayInUnit) {
        synchronized (request) {
            if (scheduler != null) {
                throw new IllegalStateException("Poller already started");
            }

            scheduler = fixedRate
                    ? executorService.scheduleAtFixedRate(new Poller(), delayInUnit, period, unit)
                    : executorService.scheduleWithFixedDelay(new Poller(), delayInUnit, period, unit);
        }
    }

    public void stop() {
        synchronized (request) {
            if (scheduler == null) {
                return;
            }

            scheduler.cancel(false);
            if (!scheduler.isDone()) {
                try {
                    scheduler.get(period * 2, unit);
                } catch (InterruptedException | ExecutionException ignore) {
                } catch (TimeoutException ignore) {
                    scheduler.cancel(true);
                }
            }

            scheduler = null;
        }
    }

    public void shutdown() {
        stop();
        executorService.shutdown();
    }

    private class Poller implements Runnable {
        @Override
        public void run() {
            try {
                LatestLivePositionList latest = request
                        .withFromDateTime(fromDateTime)
                        .fetch();

                fromDateTime = latest.getProcessedDateTime();

                synchronized (listeners) {
                    listeners.forEach(l -> l.onUpdate(latest));
                }
            } catch (Exception e) {
                synchronized (listeners) {
                    listeners.forEach(l -> l.onError(e));
                }
            }
        }
    }

    public static class Builder {
        private final LatestLivePositionRequest request;
        private TimeUnit unit;
        private Long period;
        private boolean fixedRate;

        Builder(LatestLivePositionRequest request) {
            this.request = request;
        }

        public Builder withPollingInterval(long period, TimeUnit unit) {
            this.unit = unit;
            this.period = period;
            return this;
        }

        public Builder withFixedRate(boolean fixedRate) {
            this.fixedRate = fixedRate;
            return this;
        }

        public LatestLivePositionPoller build() {
            Objects.requireNonNull(request, "Request is required");
            Objects.requireNonNull(period, "Polling interval period is required");
            Objects.requireNonNull(unit, "Polling interval unit is required");

            if (unit.toMillis(period) < 15000) {
                throw new IllegalArgumentException("Polling interval can not be less than 15 seconds");
            }

            return new LatestLivePositionPoller(this);
        }
    }
}
