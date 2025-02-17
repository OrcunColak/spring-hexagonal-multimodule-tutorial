package com.colak.messages.sys.outgoing;

import com.colak.messages.BaseMessage;
import com.colak.messages.MessageIdEnums;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class BaseOutgoingMessage extends BaseMessage {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    protected DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

    public BaseOutgoingMessage(MessageIdEnums messageId) {
        super(messageId);
    }

    public byte[] serialize() throws IOException {
        serializeHeader();
        serializeBody();
        return byteArrayOutputStream.toByteArray();
    }

    protected void serializeHeader() throws IOException {
        outputStream.writeInt(messageId.ordinal());
    }

    protected void serializeBody() {
    }

}
