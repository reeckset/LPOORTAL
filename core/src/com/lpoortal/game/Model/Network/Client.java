package com.lpoortal.game.Model.Network;

import com.badlogic.gdx.utils.Disposable;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Disposable {
    private Socket socket;
    PrintWriter output;

    public Client(){
        try {
            socket = new Socket("172.30.5.1", 8765);
            socket.setSoTimeout(0);
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Mensagem de teste");
            socket.close();
        }
        catch (IOException e) {
            System.out.println(e);
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
}
