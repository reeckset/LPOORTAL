package com.lpoortal.game.Model.Network;

import com.badlogic.gdx.utils.Disposable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Disposable {
    private Socket socket;

    PrintWriter output;
    BufferedReader input;

    public Client() {
        try {
            socket = new Socket("192.168.2.163", 8765);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    public void sendMessage() {
        System.out.println("Gonna try to send");
        if (output != null) {
            System.out.println("sending...");
            output.println("Mensagem de teste");
            output.println("Over and Out");
            System.out.println("sent!");
        }
    }

    public String readMessage() {
        if (input != null) {
            while(true) {
                try {
                    String line;
                    line = input.readLine();
                    if(line != null) {
                        System.out.println(line);
                        return line;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
