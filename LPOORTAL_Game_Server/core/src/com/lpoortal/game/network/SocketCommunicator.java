package com.lpoortal.game.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.lpoortal.game.LpoortalGame;

import view.entities.StickmanVisualDetails.Stickman_Skin;
import view.entities.TextureManager;

public class SocketCommunicator implements Runnable {

	private Socket clientSocket;
	ClientToServerMsg lastReceivedMessage;
	private long lastMessageTimestamp;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	
	private Color color;
	private Stickman_Skin skin;

	private static final long READ_FREQUENCY_MILLIS = 100;
	private long lastReadAttemptMillis = 0;
	
	public SocketCommunicator(Socket clientSocket) throws IOException {
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

	public ClientToServerMsg getLastMessage() {
		return lastReceivedMessage;
	}
	
	public void changeState(LpoortalGame.CONTROLLER_STATE state) {
		this.writeMsg(new ServerToClientMsg(state.toString()));
	}

	public Socket getClientSocket() {
		return this.clientSocket;
	}

	public void resetLastMessage() {
		lastReceivedMessage = null;
	}
	
	public void setColor(String colorStr) {
		color = TextureManager.getColorFromString(colorStr);
	}
	
	public Color getColor() {
		return color;
	}
	
	public Stickman_Skin getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = Stickman_Skin.valueOf(skin);
	}

	public long getLastMsgTimestamp() {
		return this.lastMessageTimestamp;
	}
}
