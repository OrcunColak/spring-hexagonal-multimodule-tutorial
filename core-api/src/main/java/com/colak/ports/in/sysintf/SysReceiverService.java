package com.colak.ports.in.sysintf;

import com.colak.ports.in.sysintf.model.HelloAck;

// Used by core to receive messages
public interface SysReceiverService {

    void receiveHelloAck(HelloAck helloAck);
}
