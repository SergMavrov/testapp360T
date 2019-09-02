package de.maverick;

public class TestApp {

    public static void main(String[] args) {
        SimplePost simplePost = new SimplePost();
        Player initiator = new Player("INITIATOR", simplePost);
        simplePost.registerSubscriber(initiator);
        System.out.println(initiator);
        Player partner = new Player("PARTNER", simplePost);
        simplePost.registerSubscriber(partner);
        System.out.println(partner);
        initiator.send(initiator, "TEST_MESSAGE");
    }
}
