package com.colak.messages.sys.outgoing;

import com.colak.messages.MessageIdEnums;

public class HelloMessage extends BaseOutgoingMessage {

    public HelloMessage() {
        super(MessageIdEnums.HELLO);
    }

}
