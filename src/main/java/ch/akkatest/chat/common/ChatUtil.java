package ch.akkatest.chat.common;

import java.util.*;
import java.util.concurrent.atomic.*;

/**
 * @author Mario Fusco
 */
public class ChatUtil {

    public static final String CHAT_SERVICE_NAME = "chat:service";
    public static final String CHAT_SERVICE_HOST = "localhost";
    public static final int CHAT_SERVICE_PORT = 9999;

    public static int getFreePort() {
        return new Random().nextInt(10000) + 10000;
    }
}
