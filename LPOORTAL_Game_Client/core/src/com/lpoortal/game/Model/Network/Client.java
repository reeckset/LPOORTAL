package com.lpoortal.game.Model.Network;

import com.badlogic.gdx.utils.Disposable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Disposable {
    private Socket socket;

    ObjectOutputStream output;
    ObjectInputStream input;

    public Client() {
        try {
            socket = new Socket("192.168.2.163", 8765);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println(e);
            dispose();
        }
    }

    @Override
    public void dispose() {
        try {
            output.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sendMessage(ClientToServerMsg msg){
        if(msg != null && output != null){
            try {
                output.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ServerToClientMsg readMessage() {
        if (input != null) {
            while(true) {
                try {
                    return (ServerToClientMsg) input.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
