package com.lpoortal.game.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class NetworkManager{
   
	ServerSocket socket;
	
	public static NetworkManager instance;
	public static final int defaultPort = 8888;
	
	public List<ClientToServerMsg> messages;
	
	public NetworkManager(int port) {
		instance = this;
		this.messages = new ArrayList<ClientToServerMsg>();
		try {
			socket = new ServerSocket(port);
			
			//Creates a server in an alternate thread
			Server server = new Server(socket);
			
			new Thread(server).start();
			   
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	}
	
	public static NetworkManager getInstance() {
		if (instance == null)
			instance = new NetworkManager(defaultPort);
		return instance;
	}
	
	public void updateGameWithRequest(ClientToServerMsg msg) {
		messages.add(msg);
	}
   
}