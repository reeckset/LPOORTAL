package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.network.Client;
import com.lpoortal.game.network.MessageManager;
import com.lpoortal.game.view.ConnectView;
import com.lpoortal.game.view.ControlsView;
import com.lpoortal.game.view.DrawingView;
import com.lpoortal.game.view.PlayerCustomizationView;
import com.lpoortal.game.view.TextureManager;

public class LPOORTAL_Game extends Game {

	public static LPOORTAL_Game instance;
	public enum State {PLAYER_CUSTOMIZATION_STATE, DRAWING_STATE, MOVEMENT_STATE, CONNECT_STATE};
	private State state;

	TextureManager textureManager;

	@Override
	public void create() {
		this.instance = this;
		textureManager = new TextureManager(TextureManager.Player_Color.ORANGE);
		MessageManager msgMngr = new MessageManager();
		changeState(State.PLAYER_CUSTOMIZATION_STATE);
	}

	public void changeState(State controllerState){
		switch(controllerState){
			case DRAWING_STATE:
				this.setScreen(new DrawingView());
				break;
			case MOVEMENT_STATE:
				this.setScreen(new ControlsView());
				break;
			case CONNECT_STATE:
				this.setScreen(new ConnectView());
				break;
			case PLAYER_CUSTOMIZATION_STATE:
				this.setScreen(new PlayerCustomizationView());
				break;
		}
		this.state = controllerState;
	}

	public static LPOORTAL_Game getInstance() {
		if (instance == null)
			instance = new LPOORTAL_Game();
		return instance;
	}

	public State getState(){
		return state;
	}

	public TextureManager getTextureManager() {
		return textureManager;
	}
}