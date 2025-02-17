package com.colak.messages.sys.incoming;

import com.colak.messages.BaseMessage;
import com.colak.messages.MessageIdEnums;

public abstract class BaseIncomingMessage extends BaseMessage {

    public BaseIncomingMessage(MessageIdEnums messageId) {
        super(messageId);
    }
}
