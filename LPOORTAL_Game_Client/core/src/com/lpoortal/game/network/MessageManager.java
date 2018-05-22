package com.lpoortal.game.network;

public class MessageManager {

    public static MessageManager instance;
    private Client client;
    private Thread clientThread;

    public static MessageManager getInstance() {
        if (instance == null)
            MessageManager.instance = new MessageManager();
        return instance;
    }

    private MessageManager(){
        instance = this;
    }

    public void start(String ip){
        if(this.clientThread != null) {
            try {
                clientThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.client = new Client(ip);
        clientThread = new Thread(client);
        clientThread.start();
    }

    public Client getClient() {
        return client;
    }

}
