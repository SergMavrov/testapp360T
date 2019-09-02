package de.maverick;

import java.util.ArrayList;
import java.util.List;

public class SimplePost implements Post{

    private List<Player> subscribers = new ArrayList<>();
    private String message;

    public void registerSubscriber(Player player) {
        subscribers.add(player);
    }

    public void send(Player sender, String message) {
        this.message = message;
        for (Player player : subscribers) {
            if (!player.equals(sender)) {
                player.receiveAndReply(player);
            }
        }
    }

    public String receive(Player receiver) {
        return message;
    }

}
