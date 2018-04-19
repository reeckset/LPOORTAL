package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.Model.Network.Client;
import com.lpoortal.game.View.ControlsView;
import com.lpoortal.game.View.DrawingView;
import com.lpoortal.game.View.TextureManager;

public class LPOORTAL_Game extends Game {

	private enum State {MENU_STATE, MOVEMENT_STATE, DRAWING_STATE};
	TextureManager textureManager;

	@Override
	public void create() {
		textureManager = new TextureManager(TextureManager.PlayerColor.ORANGE);
		Client client = new Client();
		client.sendMessage();
		client.readMessage();
		changeState(State.MOVEMENT_STATE);
	}

	public void changeState(State state){
		switch(state){
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