package de.maverick;

import org.apache.log4j.Logger;

public class SyncPost implements Post{

    private static final Logger LOGGER = Logger.getLogger(SyncPost.class);

    private String message;
    private Player owner;

    @Override
    public synchronized void send(Player sender, String message) {
        LOGGER.debug(String.format("SENDING state is %s", this.toString()));
        while (sender.equals(owner)){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("some error during sending", e);
            }
        }
        this.owner = sender;
        this.message = message;
        notifyAll();
    }

    @Override
    public synchronized String receive(Player receiver) {
        LOGGER.debug(String.format("RECEIVING state is %s", this.toString()));
        while (message==null || receiver.equals(owner)) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                LOGGER.error("some error during receiving", e);
            }
        }
        notifyAll();
        return message;
    }

    @Override
    public String toString() {
        return "SyncPost{" +
                "message='" + message + '\'' +
                ", owner=" + owner +
                '}';
    }
}
