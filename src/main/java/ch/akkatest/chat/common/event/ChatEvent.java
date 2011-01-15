package ch.akkatest.chat.common.event;

import java.io.*;

/**
 * @author Mario Fusco
 */
public abstract class ChatEvent implements Serializable {

    private final String username;

    public ChatEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
