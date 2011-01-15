package ch.akkatest.chat.common.event;

/**
 * @author Mario Fusco
 */
public class MessageEvent extends ChatEvent {

    private final String message;

    public MessageEvent(String username, String message) {
        super(username);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
