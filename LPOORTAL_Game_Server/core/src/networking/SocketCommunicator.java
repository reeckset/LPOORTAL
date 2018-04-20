package networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCommunicator implements Runnable {

	private Socket clientSocket;
//	private PrintWriter writer;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	
	
	public SocketCommunicator(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		this.writer = new ObjectOutputStream(clientSocket.getOutputStream());
		this.reader = new ObjectInputStream(clientSocket.getInputStream());
		
	}
	
	@Override
	public void run() {

		System.out.println(readMsg().controllerState);


	}
	
	public ClientToServerMsg readMsg() {
		if(reader != null) {
			while(true) {
				try {
					return (ClientToServerMsg) reader.readObject();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		return null;		
	}
	
	public void writeMsg(ServerToClientMsg msg) {
		
		if(msg != null && writer != null) {
			System.out.println("Sending Message...");
			try {
				writer.writeObject(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Message Sent!");
		}
		
	}

}
