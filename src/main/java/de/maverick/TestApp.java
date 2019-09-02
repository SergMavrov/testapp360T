package de.maverick;

public class TestApp {

    public static void main(String[] args) {
        Post simplePost = new SimplePost();
        Player initiator = new Player("INITIATOR", simplePost);
        System.out.println(initiator);
        Player partner = new Player("PARTNER", simplePost);
        System.out.println(partner);
        initiator.send(initiator, "TEST_MESSAGE");
    }
}
