package com.lpoortal.game.network;

public class MessageManager {

    public static MessageManager instance;
    private Client client;

    public static MessageManager getInstance() {
        if (instance == null)
            MessageManager.instance = new MessageManager();
        return instance;
    }

    private MessageManager(){
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
