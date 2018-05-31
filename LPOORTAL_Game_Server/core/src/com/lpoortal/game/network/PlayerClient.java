package com.lpoortal.game.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerClient{
	
	Socket clientSocket;
	
	/**
	 * 
	 * @param clientSocket the socket associated with the client
	 */
	public PlayerClient(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	/**
	 * 
	 * @return the output stream
	 * @throws IOException
	 */
	public OutputStream getOutputStream() throws IOException{
		return clientSocket.getOutputStream();
	}
	/**
	 * 
	 * @return the input stream
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException {
		return clientSocket.getInputStream();
	}

	/**
	 * 
	 * @return true if the socket is closed, false otherwise
	 */
	public boolean isClosed() {
		return clientSocket.isClosed();
	}
	
	/**
	 * Closes the socket
	 * @throws IOException
	 */
	public void close() throws IOException{
		clientSocket.close();
	}

	
}
