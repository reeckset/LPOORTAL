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

    public Client(){
        try {
            socket = new Socket("192.168.1.4", 8765);
            socket.setSoTimeout(0);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.println("Hi, I'm the client");
            String line;
            while((line = input.readLine()) != null) {
                System.out.println(line);
            }

        }
        catch (IOException e) {
            System.out.println(e);
            dispose();
        }
    }

    @Override
    public void dispose() {
        try {
            output.close();
            socket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sendMessage(){
        if(output != null) {
                output.println("Mensagem de teste");
        }
    }

    public void readMessage(){
        if(input != null) {
            try {
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


}
