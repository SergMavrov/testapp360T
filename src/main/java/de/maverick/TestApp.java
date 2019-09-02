package de.maverick;

public class TestApp {

    public static void main(String[] args) {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        SimplePost simplePost = new SimplePost();
        Player initiator = new Player("INITIATOR", simplePost);
        simplePost.registerSubscriber(initiator);
        System.out.println(initiator);
        Player partner = new Player("PARTNER", simplePost);
        simplePost.registerSubscriber(partner);
        System.out.println(partner);
        initiator.send(initiator, "TEST_MESSAGE");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        SyncPost syncPost = new SyncPost();
        Player syncInitiator = new Player("SYNC INITIATOR", syncPost);
        System.out.println(syncInitiator);
        Player syncPartner = new Player("SYNC PARTNER", syncPost);
        System.out.println(syncPartner);
        Thread syncInitiatorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                syncInitiator.send(syncInitiator, "SYNC_TEST_MESSAGE");
                while (true) {
                    syncInitiator.receiveAndReply(syncInitiator);
                }
            }
        }, "syncInitiatorThread");
        Thread syncPartnerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    syncPartner.receiveAndReply(syncPartner);
                }
            }
        }, "syncPartnerThread");
        syncInitiatorThread.start();
        syncPartnerThread.start();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
    }
}
