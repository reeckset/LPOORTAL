package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.network.NetworkManager;

import view.LevelScreen;
import view.entities.TextureManager;

public class LpoortalGame extends Game {
	SpriteBatch batch;
	Sprite pencilSprite;
	
	float lastReceivedX = 0;
	float lastReceivedY = 0;

	private TextureManager textureManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		pencilSprite = new Sprite(new Texture("pencil.png"));
		NetworkManager server = new NetworkManager(8765);
		textureManager = new TextureManager();
		
		startGame();

	}
	
	
	private void startGame() {
		
		setScreen(new LevelScreen(this));
	}


	@Override
	public void dispose() {
		batch.dispose();
	}
/*
	@Override
	public void render () {
		calcCurrentPencilPosition();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(NetworkManager.getInstance().getLastMessage().actionBtn) {
			textures.add(new EntityModel(curX, curY, 30, 30, new Texture("Cowboy_Hat.png")));
		}
		displayTextures();
		batch.setColor(Color.ORANGE);
		batch.draw(pencilSprite, 
				curX, curY,
				30, 30*pencilSprite.getWidth()/pencilSprite.getHeight());
		batch.setColor(Color.WHITE);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	private void calcCurrentPencilPosition() {
		curX += NetworkManager.getInstance().getLastMessage().dx - lastReceivedX;
		curY += NetworkManager.getInstance().getLastMessage().dy - lastReceivedY;
		curX = Math.max(Math.min(curX, 620), 0);
		curY = Math.max(Math.min(curY, 460), 0);
		
		lastReceivedX = NetworkManager.getInstance().getLastMessage().dx;
		lastReceivedY = NetworkManager.getInstance().getLastMessage().dy;
	}
	
	private void displayTextures() {
		for(EntityModel o : textures) {
			batch.draw(o.getTexture(), o.getX(), o.getY(), o.getWidth(), o.getHeight());
		}
	}*/
	
	public SpriteBatch getBatch() {
		return batch;
	}

	public TextureManager getTextureManager() {
		return this.textureManager;
	}
}
