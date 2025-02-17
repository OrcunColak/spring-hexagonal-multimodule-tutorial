package com.colak.messages;

public abstract class BaseIncomingMessage extends BaseMessage {

    public BaseIncomingMessage(MessageIdEnums messageId) {
        super(messageId);
    }
}
