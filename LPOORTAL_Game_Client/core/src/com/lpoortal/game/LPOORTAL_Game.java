package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.network.Client;
import com.lpoortal.game.network.MessageManager;
import com.lpoortal.game.view.ConnectView;
import com.lpoortal.game.view.ControlsView;
import com.lpoortal.game.view.DrawingView;
import com.lpoortal.game.view.TextureManager;

public class LPOORTAL_Game extends Game {

	public static LPOORTAL_Game instance;
	public enum State {MENU_STATE, DRAWING_STATE, MOVEMENT_STATE, CONNECT_STATE};
	private State state;
	TextureManager textureManager;

	@Override
	public void create() {
		this.instance = this;
		textureManager = new TextureManager(TextureManager.Player_Color.ORANGE);
		MessageManager msgMngr = new MessageManager();
		changeState(State.CONNECT_STATE);
	}

	public void changeState(State controllerState){
		switch(controllerState){
			case DRAWING_STATE:
				this.setScreen(new DrawingView(textureManager));
				break;
			case MOVEMENT_STATE:
				this.setScreen(new ControlsView(textureManager));
				break;
			case CONNECT_STATE:
				this.setScreen(new ConnectView(textureManager));
				break;
			//default:
			//	this.setScreen(new MenuView(this, textureManager));
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
}