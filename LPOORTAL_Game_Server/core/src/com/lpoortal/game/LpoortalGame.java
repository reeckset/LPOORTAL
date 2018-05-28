package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.network.NetworkManager;

import controller.GameController;
import view.entities.CountDownScreen;
import view.entities.GameOverScreen;
import view.entities.LevelScreen;
import view.entities.PlayerCustomizationScreen;
import view.entities.TextureManager;
import view.entities.WelcomeScreen;

public class LpoortalGame extends Game {
	SpriteBatch batch;
	
	float lastReceivedX = 0;
	float lastReceivedY = 0;

	private TextureManager textureManager;
	
	public enum STATE {WELCOME, PLAYER_CUSTOMIZATION, GAMEPLAY, COUNTDOWN, GAME_OVER}
	public enum CONTROLLER_STATE {CONNECT_STATE, PLAYER_CUSTOMIZATION_STATE, DRAWING_STATE, MOVEMENT_STATE, READY_STATE, GAME_OVER_STATE}
		
	private static LpoortalGame instance;
	
	@Override
	public void create () {
		this.instance = this;
		Gdx.graphics.setWindowedMode(1920, 1080);
		
		batch = new SpriteBatch();
		NetworkManager nm = NetworkManager.getInstance();
		textureManager = new TextureManager();
		
		startGame();

	}
	
	public static LpoortalGame getInstance() {
		return instance;
	}
	
	private void startGame() {
		setState(STATE.WELCOME);
	}


	@Override
	public void dispose() {
		batch.dispose();
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}

	public TextureManager getTextureManager() {
		return this.textureManager;
	}
	
	public void setState(STATE state) {
		switch(state) {
		case WELCOME:
			setScreen(new WelcomeScreen(this));
			break;
		case PLAYER_CUSTOMIZATION:
			setScreen(new PlayerCustomizationScreen(this));
			break;
		case GAMEPLAY:
			setScreen(new LevelScreen(this));
			break;
		case COUNTDOWN:
			setScreen(new CountDownScreen(this));
			break;
		case GAME_OVER:
			setScreen(new GameOverScreen(this));
			break;
		}
		
	}
	
}
