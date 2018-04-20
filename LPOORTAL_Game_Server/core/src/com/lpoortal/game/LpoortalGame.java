package com.lpoortal.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.Network.NetworkManager;
import com.lpoortal.game.Network.Server;

public class LpoortalGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		NetworkManager server = new NetworkManager(8765);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(!NetworkManager.getInstance().messages.isEmpty()) {
			batch.draw(img, NetworkManager.getInstance().messages.get(0).dx, NetworkManager.getInstance().messages.get(0).dy);
			NetworkManager.getInstance().messages.remove(0);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
