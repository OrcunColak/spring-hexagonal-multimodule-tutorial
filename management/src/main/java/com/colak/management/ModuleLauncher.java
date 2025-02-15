package com.colak.management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModuleLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosed() {

    }
}
