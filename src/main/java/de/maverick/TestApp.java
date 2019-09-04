package de.maverick;

import org.apache.log4j.Logger;

public class TestApp {

    private static final Logger LOGGER = Logger.getLogger(TestApp.class);

    private static final String INIT_MESSAGE = "TEST_MESSAGE";

    public static void main(String[] args) {
        LOGGER.debug("-=-=-=-=-=-=-=-=-=-=-");
        SimplePost simplePost = new SimplePost();
        Player initiator = new Player("INITIATOR", simplePost);
        simplePost.registerSubscriber(initiator);
        LOGGER.debug(initiator);
        Player partner = new Player("PARTNER", simplePost);
        simplePost.registerSubscriber(partner);
        LOGGER.debug(partner);
        initiator.send(INIT_MESSAGE);

        LOGGER.debug("-=-=-=-=-=-=-=-=-=-=-");
        SyncPost syncPost = new SyncPost();
        Player syncInitiator = new Player("SYNC INITIATOR", syncPost);
        LOGGER.debug(syncInitiator);
        Player syncPartner = new Player("SYNC PARTNER", syncPost);
        LOGGER.debug(syncPartner);
        Thread syncInitiatorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                syncInitiator.send(INIT_MESSAGE);
                while (!Thread.currentThread().isInterrupted()) {
                    syncInitiator.receiveAndReply();
                }
            }
        }, "syncInitiatorThread");
        Thread syncPartnerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    syncPartner.receiveAndReply();
                }
            }
        }, "syncPartnerThread");
        syncInitiatorThread.start();
        syncPartnerThread.start();
        LOGGER.debug("-=-=-=-=-=-=-=-=-=-=-");
    }
}
