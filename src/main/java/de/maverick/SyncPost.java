package de.maverick;

import java.util.Optional;

public class SyncPost implements Post{

    private String message;
    private Player owner;

    @Override
    public synchronized void send(Player sender, String message) {
        System.out.println(String.format("%s SENDING state is %s",
                Thread.currentThread().getName(), this.toString()));
        while (sender.equals(owner)){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        }
        this.owner = sender;
        this.message = message;
        notifyAll();
    }

    @Override
    public synchronized String receive(Player receiver) {
        System.out.println(String.format("%s RECEIVING state is %s",
                Thread.currentThread().getName(), this.toString()));
        while (message==null || receiver.equals(owner)) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
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
