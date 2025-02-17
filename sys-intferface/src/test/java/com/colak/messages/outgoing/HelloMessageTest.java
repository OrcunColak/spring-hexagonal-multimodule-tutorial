package com.colak.messages.outgoing;

import com.colak.messages.sys.outgoing.HelloMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HelloMessageTest {

    @Test
    void serialize() throws IOException {
        HelloMessage message = new HelloMessage();
        byte[] bytes = message.serialize();

        assertArrayEquals(new byte[]{0, 0, 0, 0}, bytes);

    }
}