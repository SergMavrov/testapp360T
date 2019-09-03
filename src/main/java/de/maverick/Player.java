package de.maverick;

import java.util.Objects;

public class Player {

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

    public void send(String message) {
        sentMessagesCounter++;
        System.out.println(String.format("%s is sending message N%d: %s", caption, sentMessagesCounter, message));
        post.send(this, message);
    }

    private String receive() {
        receivedMessagesCounter++;
        String message = post.receive(this);
        System.out.println(String.format("%s has received the message: %s", caption, message));
        return message;
    }

    public void receiveAndReply() {
        if (receivedMessagesCounter < COUNT_OF_RECEIVED_MESSAGES) {
            System.out.println(String.format("The count of received messages = %s", receivedMessagesCounter));
            String message = receive();
            if (sentMessagesCounter < COUNT_OF_SENT_MESSAGES) {
                System.out.println(String.format("The count of sent messages = %d", sentMessagesCounter));
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
