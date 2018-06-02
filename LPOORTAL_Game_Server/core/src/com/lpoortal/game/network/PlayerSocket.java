package com.lpoortal.game.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.badlogic.gdx.graphics.Color;
import com.lpoortal.game.LpoortalGame;

import view.entities.StickmanVisualDetails.Stickman_Skin;
import view.entities.TextureManager;

public class PlayerSocket implements Runnable {

	private Socket clientSocket;
	ClientToServerMsg lastReceivedMessage;
	private long lastMessageTimestamp;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	
	private Color color;
	private Stickman_Skin skin;
	private String name;

	private static final long READ_FREQUENCY_MILLIS = 100;
	private long lastReadAttemptMillis = 0;
	
	/**
	 * Creates a communicator to abstract the client/server communication functionalities
	 * @param clientSocket the socket to communicate with the client
	 * @throws IOException
	 */
	public PlayerSocket(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		this.writer = new ObjectOutputStream(clientSocket.getOutputStream());
		this.reader = new ObjectInputStream(clientSocket.getInputStream());
	}
	
	@Override
	public void run() {
		while(!clientSocket.isClosed()) {
			if(System.currentTimeMillis() - lastReadAttemptMillis >= READ_FREQUENCY_MILLIS) {
				lastReadAttemptMillis = System.currentTimeMillis();
				readMsg();
			}
		}
	}
	
	/**
	 * Reads the next available message in the socket
	 */
	public void readMsg() {
		if(reader != null) {
			while(true) {
				try {
					ClientToServerMsg msg;
					if((msg = (ClientToServerMsg) reader.readObject()) != null) {
						setLastReceivedMsg(msg);
					}
				} catch (Exception e) {
					disposeClient();
				}
			}
		}		
	}
	
	/**
	 * Writes a message to the socket
	 * @param msg Message Object
	 */
	public void writeMsg(ServerToClientMsg msg) {
		
		
		if(msg != null && writer != null) {
			try {
				writer.writeObject(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void disposeClient() {
		this.clientSocket = null;
	}

	private void setLastReceivedMsg(ClientToServerMsg msg) {
		lastReceivedMessage = msg;
		lastMessageTimestamp = System.currentTimeMillis();
	}

	/**
	 * 
	 * @return the latest message received
	 */
	public ClientToServerMsg getLastMessage() {
		return lastReceivedMessage;
	}
	
	/**
	 * Sends a message to the client so that it changes state
	 * @param state new state
	 */
	public void changeState(LpoortalGame.CONTROLLER_STATE state) {
		this.writeMsg(new ServerToClientMsg(state.toString()));
	}

	/**
	 * 
	 * @return the socket
	 */
	public Socket getClientSocket() {
		return this.clientSocket;
	}

	/**
	 * Sets the latest message to null
	 */
	public void resetLastMessage() {
		lastReceivedMessage = null;
	}
	
	/**
	 * Sets the color to the specified one
	 * @param colorStr color in string format
	 */
	public void setColor(String colorStr) {
		color = TextureManager.getColorFromString(colorStr);
	}
	
	/**
	 * 
	 * @return the client color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return the client skin
	 */
	public Stickman_Skin getSkin() {
		return skin;
	}

	/**
	 * Sets the skin to the specified one
	 * @param skin skin in string format
	 */
	public void setSkin(String skin) {
		this.skin = Stickman_Skin.valueOf(skin);
	}

	/**
	 * 
	 * @return the timestamp of the last received message
	 */
	public long getLastMsgTimestamp() {
		return this.lastMessageTimestamp;
	}

	/**
	 * Sets the name to the specified one
	 * @param name name in string format
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}
}
