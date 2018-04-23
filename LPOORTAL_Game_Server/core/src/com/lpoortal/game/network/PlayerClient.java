package com.lpoortal.game.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerClient{
	
	Socket clientSocket;
	ClientToServerMsg lastReceivedMessage;
	
	public PlayerClient(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	public OutputStream getOutputStream() throws IOException{
		return clientSocket.getOutputStream();
	}

	public InputStream getInputStream() throws IOException {
		return clientSocket.getInputStream();
	}

	public boolean isClosed() {
		return clientSocket.isClosed();
	}
	
	public void close() throws IOException{
		clientSocket.close();
	}

	public void setLastReceivedMsg(ClientToServerMsg msg) {
		lastReceivedMessage = msg;		
	}

	public ClientToServerMsg getLastMessage() {
		return lastReceivedMessage;
	}
}
