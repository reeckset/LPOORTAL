package com.lpoortal.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoortal.game.Network.NetworkManager;
import com.lpoortal.game.Network.Server;

public class LpoortalGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite pencilSprite;
	
	float lastReceivedX = 0;
	float lastReceivedY = 0;

	float curX = 300;
	float curY = 300;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		pencilSprite = new Sprite(new Texture("pencil.png"));
		NetworkManager server = new NetworkManager(8765);

	}

	@Override
	public void render () {
		
		calcCurrentPencilPosition();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
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
	
	
}
