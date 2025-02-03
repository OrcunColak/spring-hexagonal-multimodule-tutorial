package com.colak.messages.outgoing;

import com.colak.messages.MessageIdEnums;

public class HelloMessage extends BaseOutgoingMessage {

    public HelloMessage() {
        super(MessageIdEnums.HELLO);
    }

}
