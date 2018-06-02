package com.lpoortal.game.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;


public class NetworkManager{
   
	static final int DEFAULT_PORT = 8765;
	
	ServerSocket socket;
	
	public static NetworkManager instance;
	
	PlayerSocket player1Communicator;
	PlayerSocket player2Communicator;
	
	

	private NetworkManager() {
		instance = this;
		
		try {
			socket = new ServerSocket(DEFAULT_PORT);
			//Creates a server in an alternate thread
			Server server = new Server(socket);
			
			new Thread(server).start();
			   
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	}
	
	/**
	 * 
	 * @return the instance of the network manager
	 */
	public static NetworkManager getInstance() {
		if (instance == null)
			instance = new NetworkManager();
		return instance;
	}
	
	/**
	 * Creates the communicator for the respective player
	 * @param playerCommunicator the player SocketCommunicator
	 */
	public void addPlayerClient(PlayerSocket playerCommunicator) {
		if(!isPlayer1Connected()) {
			this.player1Communicator = playerCommunicator;
			System.out.println("Player 1 connected");
		}else if(!isPlayer2Connected()) {
			this.player2Communicator = playerCommunicator;
			System.out.println("Player 2 connected");
		}
	}
	
	/**
	 * 
	 * @return player 1 communicator
	 */
	public PlayerSocket getPlayer1() {
		return player1Communicator;
	}
	
	/**
	 * 
	 * @return player 2 communicator
	 */
	public PlayerSocket getPlayer2() {
		return player2Communicator;
	}
   
	
	/**
	 * 
	 * @return the host machine ip address to connect to
	 */
	public String getHostIp() {
		try {
			
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
	        for (;netInterfaces.hasMoreElements();) { //Iterate over network devices
	        	NetworkInterface currNetInterface = (NetworkInterface) netInterfaces.nextElement();
	        	
	        	Enumeration ipAddresses = currNetInterface.getInetAddresses();
	            for (;ipAddresses.hasMoreElements();) { //Get ipAddresses associated with this device
	                InetAddress ipAddress = (InetAddress) ipAddresses.nextElement();
	                if (!ipAddress.isLoopbackAddress() && ipAddress.isSiteLocalAddress()) {
	                        return ipAddress.toString().substring(1); //Remove the slash before the ip address
	                }
	            }
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
        return null;
	}
	
	/**
	 * 
	 * @return true if the player 1 is connected
	 */
	public boolean isPlayer1Connected() {
		return this.player1Communicator != null && this.player1Communicator.getClientSocket() != null;
	}
	
	/**
	 * 
	 * @return true if the player 2 is connected
	 */
	public boolean isPlayer2Connected() {
		return this.player2Communicator != null && this.player2Communicator.getClientSocket() != null;
	}
}