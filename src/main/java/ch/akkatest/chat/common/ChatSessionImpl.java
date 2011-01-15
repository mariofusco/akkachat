package ch.akkatest.chat.common;

import akka.actor.*;

/**
 * @author Mario Fusco
 */
public class ChatSessionImpl extends akka.actor.TypedActor implements ChatSession {

    public void printMessage(String sender, String message) {
        System.out.println(sender + ": " + message);
    }
}
