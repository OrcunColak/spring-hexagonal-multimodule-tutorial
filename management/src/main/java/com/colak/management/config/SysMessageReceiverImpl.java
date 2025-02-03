package com.colak.management.config;

import lombok.extern.slf4j.Slf4j;
import com.colak.sysintf.in.SysMessageReceiver;

@Slf4j
public class SysMessageReceiverImpl implements SysMessageReceiver {

    @Override
    public void receiveHelloAck() {

        log.info("receiveHello");

    }
}
