package de.maverick;

public interface Post {

    void send(Player sender, String message);

    String receive(Player receiver);
}
