package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.Model.Network.Client;
import com.lpoortal.game.Model.Network.ClientToServerMsg;
import com.lpoortal.game.View.ControlsView;
import com.lpoortal.game.View.DrawingView;
import com.lpoortal.game.View.TextureManager;

public class LPOORTAL_Game extends Game {

	private enum State {MENU_STATE, DRAWING_STATE, MOVEMENT_STATE, CONNECT_STATE};
	TextureManager textureManager;

	@Override
	public void create() {
		textureManager = new TextureManager(TextureManager.Player_Color.ORANGE);
		Client client = new Client();
		client.sendMessage();
		client.readMessage();
		changeState(State.MOVEMENT_STATE);

		ClientToServerMsg clientToServerMsg = new ClientToServerMsg();
		clientToServerMsg.controller_state = State.MENU_STATE.toString();
	}

	public void changeState(State controllerState){
		switch(controllerState){
			case DRAWING_STATE:
				this.setScreen(new DrawingView(this, textureManager));
				break;
			case MOVEMENT_STATE:
				this.setScreen(new ControlsView(this, textureManager));
				break;
			//default:
			//	this.setScreen(new MenuView(this, textureManager));
		}
	}


}