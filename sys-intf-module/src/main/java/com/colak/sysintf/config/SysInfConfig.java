package com.colak.sysintf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.colak.ports.in.sysintf.SysMessageReceiver;

@Configuration
@EnableScheduling
public class SysInfConfig {

    @Bean
    HelloAckScheduledTask myScheduledTask(SysMessageReceiver sysMessageReceiver) {
        return new HelloAckScheduledTask(sysMessageReceiver);
    }
}
