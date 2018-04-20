package com.lpoortal.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.Network.NetworkManager;
import com.lpoortal.game.Network.Server;

public class LpoortalGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite pencilSprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		pencilSprite = new Sprite(new Texture("pencil.png"));
		pencilSprite.setColor(Color.ORANGE);
		NetworkManager server = new NetworkManager(8765);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(pencilSprite, NetworkManager.getInstance().getLastMessage().dx, NetworkManager.getInstance().getLastMessage().dy, 30, 30);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
