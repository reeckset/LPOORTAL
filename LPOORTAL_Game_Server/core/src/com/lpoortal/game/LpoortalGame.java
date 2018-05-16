package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.network.NetworkManager;

import view.entities.LevelScreen;
import view.entities.TextureManager;
import view.entities.WelcomeScreen;

public class LpoortalGame extends Game {
	SpriteBatch batch;
	
	float lastReceivedX = 0;
	float lastReceivedY = 0;

	private TextureManager textureManager;
	
	public enum STATE {WELCOME, PLAYER_CUSTOMIZATION, GAMEPLAY}
		
	@Override
	public void create () {
		
		Gdx.graphics.setWindowedMode(1920, 1080);
		
		batch = new SpriteBatch();
		NetworkManager nm = NetworkManager.getInstance();
		textureManager = new TextureManager();
		
		startGame();

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
		case GAMEPLAY:
			setScreen(new LevelScreen(this));
			break;
		case WELCOME:
			setScreen(new WelcomeScreen(this));
			break;
		}
	}
	
}
