package com.lpoortal.game.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Server implements Runnable{

	private ServerSocket socket;
	private SocketCommunicator socketCommunicator;
	
	public Server(ServerSocket socket) {
		this.socket = socket;
		
	}

	@Override
	public void run() {
		try {
			while(true) {
				Socket clientSocket = socket.accept();
		        clientSocket.setTcpNoDelay(true);
		        socketCommunicator = new SocketCommunicator(clientSocket);
		        NetworkManager.getInstance().addPlayerClient(socketCommunicator);
		        new Thread(socketCommunicator).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
	    }
		
	}
	


}
