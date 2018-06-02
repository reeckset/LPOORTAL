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

    private boolean isRunning = true;

    /**
     * Starts the client, connecting to the server socket
     * @param ip connection ip address
     */
    public Client(String ip) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, 8765), 1000);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            nextSendingMessage = new ClientToServerMsg();
        } catch (Exception e) {
            StateController.getInstance().setNextState(LPOORTAL_Game.State.CONNECT_STATE);
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

    /**
     * Sends specified message to Server
     * @param msg message to send
     */
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

    /**
     * Reads the latest available message, if there is one, or waits for one
     * @return read message
     */
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
        isRunning = false;
        dispose();
        StateController.getInstance().setNextState(LPOORTAL_Game.State.CONNECT_STATE);
    }


    @Override
    public void run() {
        while(isRunning) {
            if (socket != null && !socket.isClosed() && nextSendingMessage != null && nextSendingMessage.controllerState != ""
                    && System.currentTimeMillis() - lastSentMessageMillis >= MESSAGE_SEND_FREQUENCY_MILLIS) {
                sendMessage(nextSendingMessage);
                System.out.println(nextSendingMessage.controllerState + ", " + nextSendingMessage.actionBtn);
                lastSentMessageMillis = System.currentTimeMillis();
            }
        }
    }

    /**
     * Sets the next message to be sent
     * @param nextSendingMessage Messsage to send
     */
    public void setNextSendingMessage(ClientToServerMsg nextSendingMessage){
        this.nextSendingMessage = nextSendingMessage;
    }
}
