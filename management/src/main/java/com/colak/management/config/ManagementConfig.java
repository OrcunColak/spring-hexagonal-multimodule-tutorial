package com.colak.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.colak.sysintf.in.SysMessageReceiver;

@Configuration
public class ManagementConfig {

    @Bean
    SysMessageReceiver sysMessageReceiver () {
        return new SysMessageReceiverImpl();
    }
}
