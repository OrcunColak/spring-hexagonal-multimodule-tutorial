package com.colak.messages;

import lombok.Getter;

@Getter
public abstract class BaseMessage {

    protected MessageIdEnums messageId;

    public BaseMessage(MessageIdEnums messageId) {
        this.messageId = messageId;
    }
}
