package com.lpoortal.game.network;

public class MessageManager {

    public static MessageManager instance;
    private Client client;
    private Thread clientThread;

    /**
     *
     * @return this manager instance
     */
    public static MessageManager getInstance() {
        if (instance == null)
            MessageManager.instance = new MessageManager();
        return instance;
    }

    private MessageManager(){
        instance = this;
    }

    /**
     * Starts the manager with specified ip
     * @param ip game server address
     */
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

    /**
     *
     * @return client
     */
    public Client getClient() {
        return client;
    }

}
