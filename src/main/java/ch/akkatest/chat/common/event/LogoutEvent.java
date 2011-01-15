package ch.akkatest.chat.common.event;

/**
 * @author Mario Fusco
 */
public class LogoutEvent extends ChatEvent {

    public LogoutEvent(String username) {
        super(username);
    }
}
