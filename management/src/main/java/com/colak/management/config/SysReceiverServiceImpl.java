package com.colak.management.config;

import com.colak.core.api.ports.in.sysintf.model.HelloAck;
import lombok.extern.slf4j.Slf4j;
import com.colak.core.api.ports.in.sysintf.SysReceiverService;

@Slf4j
public class SysReceiverServiceImpl implements SysReceiverService {

    @Override
    public void receiveHelloAck(HelloAck helloAck) {

        log.info("receiveHello");

    }
}
