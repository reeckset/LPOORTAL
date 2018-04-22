package com.lpoortal.game.network;

public class MessageManager {

    public static MessageManager instance;
    private Client client;

    public static MessageManager getInstance() {
        if (instance == null)
            System.out.println("Network Manager not available");
        return instance;
    }

    public MessageManager(){
        instance = this;
    }

    public void start(String ip){
        this.client = new Client(ip);
        new Thread(client).start();
    }

    public Client getClient() {
        return client;
    }

}
