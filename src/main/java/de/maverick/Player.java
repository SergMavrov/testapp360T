package de.maverick;

import org.apache.log4j.Logger;

import java.util.Objects;

/**
 * The class which can send and receive some text messages.
 */
public class Player {

    private static final Logger LOGGER = Logger.getLogger(Player.class);

    private static final byte COUNT_OF_SENT_MESSAGES = 10;
    private static final byte COUNT_OF_RECEIVED_MESSAGES = 10;

    private String caption;
    private byte sentMessagesCounter;
    private byte receivedMessagesCounter;
    private Post post;

    public Player(String caption, Post post) {
        this.caption = caption;
        this.post = post;
        this.sentMessagesCounter = 0;
        this.receivedMessagesCounter = 0;
    }

    /**
    *   Send the message.
     */
    public void send(String message) {
        sentMessagesCounter++;
        LOGGER.debug(String.format("%s is sending message N%d: %s", caption, sentMessagesCounter, message));
        post.send(this, message);
    }

    private String receive() {
        receivedMessagesCounter++;
        String message = post.receive(this);
        LOGGER.debug(String.format("%s has received the message: %s", caption, message));
        return message;
    }

    /**
     * Receive the message.
     */
    public void receiveAndReply() {
        if (receivedMessagesCounter < COUNT_OF_RECEIVED_MESSAGES) {
            LOGGER.debug(String.format("%s has the count of received messages = %s", caption, receivedMessagesCounter));
            String message = receive();
            if (sentMessagesCounter < COUNT_OF_SENT_MESSAGES) {
                LOGGER.debug(String.format("%s has the count of sent messages = %d", caption, sentMessagesCounter));
                send(String.format("%s-%d", message, sentMessagesCounter));
            }
        } else {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return caption.equals(player.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption);
    }

    @Override
    public String toString() {
        return "Player{" +
                "caption='" + caption + '\'' +
                '}';
    }
}
