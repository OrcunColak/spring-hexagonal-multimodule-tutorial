package com.colak.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.colak.ports.in.sysintf.SysReceiverService;

@Configuration
public class ManagementConfig {

    @Bean
    SysReceiverService sysMessageReceiver () {
        return new SysReceiverServiceImpl();
    }
}
