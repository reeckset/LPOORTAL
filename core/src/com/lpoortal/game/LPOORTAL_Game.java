package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.Model.Network.Client;
import com.lpoortal.game.View.ControlsView;
import com.lpoortal.game.View.MovementView;
import com.lpoortal.game.View.TextureManager;

import java.io.ObjectOutputStream;

public class LPOORTAL_Game extends Game {

	private enum State {MENU_STATE, MOVEMENT_STATE, DRAWING_STATE};
	TextureManager textureManager;

	@Override
	public void create() {
		textureManager = new TextureManager(TextureManager.PlayerColor.ORANGE);
		changeState(State.DRAWING_STATE);
	}

	public void changeState(State state){
		switch(state){
			case MOVEMENT_STATE:
				this.setScreen(new MovementView(this, textureManager));
				break;
			case DRAWING_STATE:
				this.setScreen(new ControlsView(this, textureManager));
				break;
			//default:
			//	this.setScreen(new MenuView(this, textureManager));
		}
	}


}