package ch.akkatest.chat.common.event;

/**
 * @author Mario Fusco
 */
public class LoginEvent extends ChatEvent {

    private final String clientHost;
    private final int clientPort;

    public LoginEvent(String clientHost, int clientPort, String username) {
        super(username);
        this.clientHost = clientHost;
        this.clientPort = clientPort;
    }

    public String getClientHost() {
        return clientHost;
    }

    public int getClientPort() {
        return clientPort;
    }
}
