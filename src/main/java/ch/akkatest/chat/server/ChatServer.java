package ch.akkatest.chat.server;

import static ch.akkatest.chat.common.ChatUtil.*;

import ch.akkatest.chat.common.*;
import ch.akkatest.chat.common.event.*;

import static akka.actor.Actors.*;

import akka.actor.TypedActor;
import akka.actor.UntypedActor;

import java.util.*;

/**
 * @author Mario Fusco
 */
public class ChatServer {

    private static final Map<String, ChatSession> sessions = new HashMap<String, ChatSession>();

    public static void main(String[] args) {
        new ChatServer().start();
    }

    private void start() {
        remote().start(CHAT_SERVICE_HOST, CHAT_SERVICE_PORT, getClass().getClassLoader()).register(CHAT_SERVICE_NAME, actorOf(ChatServerListener.class));
    }

    public static class ChatServerListener extends UntypedActor {
        public void onReceive(Object event) {
            if (event instanceof LoginEvent) doLogin((LoginEvent)event);
            else if (event instanceof MessageEvent) broadcastMessage((MessageEvent)event);
            else if (event instanceof LogoutEvent) doLogout((LogoutEvent)event);
        }

        private void doLogin(LoginEvent login) {
            ChatSession session = (ChatSession)TypedActor.newRemoteInstance(ChatSession.class, ChatSessionImpl.class, login.getClientHost(), login.getClientPort());
            String username = login.getUsername();
            sessions.put(username, session);
            System.out.println(username + " just logged in");
            broadcastMessage(username, "I just logged in");
        }

        private void broadcastMessage(MessageEvent messageEvent) {
            broadcastMessage(messageEvent.getUsername(), messageEvent.getMessage());
        }

        private void broadcastMessage(String sender, String message) {
            System.out.println(sender + " sent: " + message);
            for (Map.Entry<String, ChatSession> entry : sessions.entrySet()) {
                if (!entry.getKey().equals(sender)) entry.getValue().printMessage(sender, message);
            }
        }

        private void doLogout(LogoutEvent logout) {
            String username = logout.getUsername();
            ChatSession session = sessions.remove(username);
            TypedActor.stop(session);
            System.out.println(username + " just logged out");
            broadcastMessage(username, "I just logged out");
        }
    }
}
