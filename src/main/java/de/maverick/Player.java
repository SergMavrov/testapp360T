package de.maverick;

import java.util.Objects;

public class Player {

    private static final byte LIMIT_OF_MESSAGES = 10;

    private String caption;
    private Byte messageCounter = 0;
    private Post post;

    public Player(String caption, Post post) {
        this.caption = caption;
        this.post = post;
        ((SimplePost)this.post).registerSubscriber(this);
    }

    public void send(Player sender, String message) {
        messageCounter++;
        System.out.println(String.format("%s is sending message N%d: %s", caption, messageCounter, message));
        post.send(sender, message);
    }

    public void receiveAndReply() {
        String message = post.receive();
        System.out.println(String.format("%s has received the message: %s", caption, message));
        System.out.println(String.format("The count of messages = %d", messageCounter));
        if (messageCounter < LIMIT_OF_MESSAGES) {
            send(this, String.format("%s-%d", message, messageCounter));
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
