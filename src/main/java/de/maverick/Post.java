package de.maverick;

/**
 * The interface defines methods for the object-transport of messages.
 */
public interface Post {

    /**
     * Send the message.
     * @param sender object who sends the message.
     * @param message some text message.
     */
    void send(Player sender, String message);

    /**
     * Receive the message.
     * @param receiver object who receives the message.
     * @return the received message.
     */
    String receive(Player receiver);
}
