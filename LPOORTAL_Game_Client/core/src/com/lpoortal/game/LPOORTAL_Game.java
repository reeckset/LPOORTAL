package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.network.MessageManager;
import com.lpoortal.game.controller.StateController;
import com.lpoortal.game.view.ConnectView;
import com.lpoortal.game.view.ControlsView;
import com.lpoortal.game.view.DrawingView;
import com.lpoortal.game.view.GameOverView;
import com.lpoortal.game.view.PlayerCustomizationView;
import com.lpoortal.game.view.ReadyView;
import com.lpoortal.game.view.TextureManager;

public class LPOORTAL_Game extends Game {

	public static LPOORTAL_Game instance;
	public enum State {PLAYER_CUSTOMIZATION_STATE, DRAWING_STATE, MOVEMENT_STATE, CONNECT_STATE, READY_STATE, GAME_OVER_STATE};
	private State state;

	private String playerColor;
	private String playerName;
	private String playerSkin;

	TextureManager textureManager;

	@Override
	public void create() {
		this.instance = this;
		textureManager = new TextureManager();
		StateController msgReceiver = StateController.getInstance();
		new Thread(msgReceiver).start();
		changeState(State.CONNECT_STATE);
	}

	public void changeState(State controllerState){
		this.state = controllerState;
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
			case READY_STATE:
				this.setScreen(new ReadyView());
				break;
			case GAME_OVER_STATE:
				this.setScreen(new GameOverView());
				break;
		}
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

	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}

	public String getPlayerSkin() {
		return playerSkin;
	}

	public void setPlayerSkin(String playerSkin) {
		this.playerSkin = playerSkin;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}