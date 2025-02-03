package com.colak.sysintf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.colak.sysintf.in.SysMessageReceiver;

@Configuration
@EnableScheduling
public class SysInfConfig {

    @Bean
    MyScheduledTask myScheduledTask(SysMessageReceiver sysMessageReceiver) {
        return new MyScheduledTask(sysMessageReceiver);
    }
}
