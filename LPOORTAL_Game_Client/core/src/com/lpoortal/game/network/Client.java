package com.lpoortal.game.network;

import com.badlogic.gdx.utils.Disposable;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.controller.StateController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client implements Disposable, Runnable {
    private Socket socket;

    ObjectOutputStream output;
    ObjectInputStream input;

    private ClientToServerMsg nextSendingMessage;
    private long lastSentMessageMillis = 0;

    private static final int MESSAGE_SEND_FREQUENCY_MILLIS = 30;

    public Client(String ip) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, 8765), 1000);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            nextSendingMessage = new ClientToServerMsg();
        } catch (Exception e) {
            LPOORTAL_Game.getInstance().changeState(LPOORTAL_Game.State.CONNECT_STATE);
        }
    }

    @Override
    public void dispose() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sendMessage(ClientToServerMsg msg){
        if(msg != null && output != null){
            try {
                output.writeObject(msg);
                output.flush();
                output.reset();
            }catch (Exception e){
                closeSocket();
            }
        }
    }

    public ServerToClientMsg readMessage() {
        while(input != null) {
            try {
                return (ServerToClientMsg) input.readObject();
            } catch (Exception e) {
                closeSocket();
            }
        }
        return null;
    }

    private void closeSocket(){
        dispose();
        StateController.getInstance().setNextState(LPOORTAL_Game.State.CONNECT_STATE);
    }


    @Override
    public void run() {
        while(true) {
            if (socket != null && !socket.isClosed() && nextSendingMessage != null && nextSendingMessage.controllerState != ""
                    && System.currentTimeMillis() - lastSentMessageMillis >= MESSAGE_SEND_FREQUENCY_MILLIS) {
                sendMessage(nextSendingMessage);
                lastSentMessageMillis = System.currentTimeMillis();
            }
        }
    }

    public void setNextSendingMessage(ClientToServerMsg nextSendingMessage){
        this.nextSendingMessage = nextSendingMessage;
    }

    public boolean isOpen(){
        return socket != null && !socket.isClosed();
    }
}
