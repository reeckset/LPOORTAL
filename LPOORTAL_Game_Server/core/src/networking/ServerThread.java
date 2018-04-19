package networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.badlogic.gdx.Gdx;

public class ServerThread extends Thread {

	private ServerSocket server;
	
	public ServerThread(ServerSocket server) {
		this.server = server;
	}
	
	@Override
	public void run() {
	    try {
	       server = new ServerSocket(8765);
	       try {
	          Socket serverSocket = server.accept();
	          readMsg(serverSocket);
	           }
	       catch (IOException e) {
	          System.out.println(e);
	       }
	       
	      
        }
        catch (IOException e) {
           System.out.println(e);
        }
		
		Gdx.app.exit();
	}

	private void readMsg(Socket serverSocket) {
		DataInputStream input;
	    try {
	       input = new DataInputStream(serverSocket.getInputStream());
	       byte[] msg = new byte[1024];
	       input.readFully(msg);
	       
	       System.out.println(msg);
	    }
	    catch (IOException e) {
	       System.out.println(e);
	    }
		
	}

}
