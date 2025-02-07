package com.colak.eventpublishing;

public interface EventPublishingService {

    void publish(Object event);

    void register(Object subscriber);

    void unregister(Object subscriber);

}
