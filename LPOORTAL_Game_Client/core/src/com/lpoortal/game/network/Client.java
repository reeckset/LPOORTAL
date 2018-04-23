package com.lpoortal.game.network;

import com.badlogic.gdx.utils.Disposable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client implements Disposable, Runnable {
    private Socket socket;

    ObjectOutputStream output;
    ObjectInputStream input;

    private ClientToServerMsg nextSendingMessage;
    private long lastSentMessageMillis = 0;

    private static final int MESSAGE_SEND_FREQUENCY_MILLIS = 100;

    public Client(String ip) {
        try {
            socket = new Socket(ip, 8765);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            nextSendingMessage = new ClientToServerMsg();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void dispose() {
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sendMessage(ClientToServerMsg msg){
        if(msg != null && output != null){
            try {
                output.writeObject(msg);
            }catch (SocketException e){
                closeSocket();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ServerToClientMsg readMessage() {
        if (input != null) {
            while(true) {
                try {
                    return (ServerToClientMsg) input.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(socket != null && !socket.isClosed()) {
            if (nextSendingMessage.controllerState != "" && System.currentTimeMillis() - lastSentMessageMillis >= MESSAGE_SEND_FREQUENCY_MILLIS) {
                sendMessage(nextSendingMessage);
                nextSendingMessage = new ClientToServerMsg();
            }
        }
    }

    public void setNextSendingMessage(ClientToServerMsg nextSendingMessage){
        this.nextSendingMessage = nextSendingMessage;
    }
}
