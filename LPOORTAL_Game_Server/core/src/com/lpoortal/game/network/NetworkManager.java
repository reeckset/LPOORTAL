package com.lpoortal.game.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;


public class NetworkManager{
   
	ServerSocket socket;
	
	public static NetworkManager instance;
	public static final int defaultPort = 8888;
	
	PlayerClient player1Socket;
	PlayerClient player2Socket;
	
	ClientToServerMsg lastMessage;
	
	public ClientToServerMsg getLastMessage() {
		return lastMessage;
	}

	public NetworkManager(int port) {
		instance = this;
		lastMessage = new ClientToServerMsg();
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
		lastMessage = msg;
	}
	
	public void addPlayerClient(PlayerClient client) {
		if(this.player1Socket == null) {
			this.player1Socket = client;
			System.out.println("Player 1 connected");
		}else if(this.player2Socket == null) {
			this.player2Socket = client;
			System.out.println("Player 2 connected");
		}
	}
	
	public PlayerClient getPlayer1() {
		return player1Socket;
	}
	
	public PlayerClient getPlayer2() {
		return player2Socket;
	}
   
}