package com.colak.sysintf.config;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import com.colak.ports.in.sysintf.SysMessageReceiver;

@RequiredArgsConstructor
public class HelloAckScheduledTask {

    private final SysMessageReceiver sysMessageReceiver;

    // Runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void runTask() {
        sysMessageReceiver.receiveHelloAck();
    }
}
