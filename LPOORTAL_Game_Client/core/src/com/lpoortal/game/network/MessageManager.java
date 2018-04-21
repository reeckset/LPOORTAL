package com.lpoortal.game.network;

public class MessageManager {

    public static MessageManager instance;
    private Client client;

    public static MessageManager getInstance() {
        if (instance == null)
            instance = new MessageManager(new Client());
        return instance;
    }

    public MessageManager(Client client){
        this.client = client;
        new Thread(client).start();
    }

}
