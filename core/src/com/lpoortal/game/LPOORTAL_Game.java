package com.lpoortal.game;

import com.badlogic.gdx.Game;
import com.lpoortal.game.View.ControlsView;
import com.lpoortal.game.View.TextureManager;

public class LPOORTAL_Game extends Game {

	@Override
	public void create() {

		TextureManager textureManager = new TextureManager(TextureManager.PlayerColor.ORANGE);
		
		this.setScreen(new ControlsView(this, textureManager));

	}
}