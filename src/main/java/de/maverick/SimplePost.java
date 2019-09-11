package de.maverick;

import java.util.HashMap;
import java.util.Map;

/**
 * The simple object-transporter. It is working in one thread.
 */
public class SimplePost implements Post{

    private Map<Player, String> messages = new HashMap<>();

    public void send(Player sender, String message) {
        messages.put(sender, message);
    }

    public String receive(Player receiver) {
        for (Player player : messages.keySet()) {
            if (!player.equals(receiver)) {
                String message = messages.get(player);
                messages.remove(player);
                return message;
            }
        }
        throw new IllegalStateException("no messages from the another player");
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }
}
