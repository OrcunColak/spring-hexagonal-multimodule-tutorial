package com.colak.eventpublishing.impl;

import com.colak.eventpublishing.EventPublishingService;
import com.google.common.eventbus.EventBus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventPublishingServiceImpl implements EventPublishingService {

    private final EventBus eventBus;

    @Override
    public void publish(Object event) {
        eventBus.post(event);
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }
}
