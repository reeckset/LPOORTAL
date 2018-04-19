package networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCommunicator implements Runnable {

	private Socket clientSocket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	
	public SocketCommunicator(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
		this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
	}
	
	@Override
	public void run() {
		
		//Read Message
		readMsg();
		writeMsg("Hi, I'm server!");

	}
	
	private void readMsg() {
		
		String line = "Default Line";
	    try {
	    	
	    	while ((line = reader.readLine()) != null) {
	           System.out.println(line); 
	        }
	    }
	    catch (IOException e) {
	    	System.out.println("Couldn't read:");
	    	System.out.println(line);
	    	e.printStackTrace();
	    }
		
	}
	
	private void writeMsg(String msg) {
		System.out.println("Sending Message...");
		writer.println(msg);
		System.out.println("Message Sent!");
	}

}
