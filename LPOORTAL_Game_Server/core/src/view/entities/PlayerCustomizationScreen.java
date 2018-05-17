package view.entities;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.ServerToClientMsg;
import com.lpoortal.game.network.SocketCommunicator;

import controller.PlayerCustomizationController;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;
import view.entities.TextureManager.GUI_Texture;

public class PlayerCustomizationScreen extends GUIScreen {

	SpriteBatch spriteBatch = new SpriteBatch();
	
	final static float WIDTH = Gdx.graphics.getWidth();
	final static float HEIGHT = Gdx.graphics.getHeight();
	
	PlayerCustomizationController controller;
	
	Sprite player1Stickman, player2Stickman;
	
	Label player1NameLabel, player2NameLabel;
	
	float time = 0f;
	
    public PlayerCustomizationScreen(LpoortalGame game) {
        super(game);
        controller = new PlayerCustomizationController(game);
        createUI();
    }
    
    @Override
    public void render(float delta) {
    	super.render(delta);
    	time += delta;
		spriteBatch.begin();
    	updateStickmen();
    	
		spriteBatch.end();
    }

	private void createUI(){
    	showHeader();
    	createStickmenSprites();
    	showPlayerNames();
    }
	
	private void showPlayerNames() {
		player1NameLabel = showSubText("Player 1 Name", 100, 200, 200, 40);
		player1NameLabel.setFontScale(0.4f);
		player1NameLabel.setAlignment(Align.center);
		player2NameLabel = showSubText("Player 2 Name", 375, 210, 200, 40);
		player2NameLabel.setFontScale(0.4f);
		player2NameLabel.setAlignment(Align.center);
		
	}

	private void showHeader() {
		showImage(GUI_Texture.LOGO, 440, 260, 200, 100);
    	Label title = showSubText("Customize your Stickmen!", 20, 265, VP_WIDTH, 80);
    	title.setFontScale(0.4f);
	}
	
	private void updateStickmen() {
		player1Stickman.setRegion(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.RIGHT).getKeyFrame(time, true));
		player2Stickman.setRegion(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.LEFT).getKeyFrame(time, true));
		
		ClientToServerMsg msgPlayer1 = NetworkManager.getInstance().getPlayer1().getLastMessage();
		if(msgPlayer1 != null && msgPlayer1.playerColor != null) {
			player1Stickman.setColor(TextureManager.getColorFromString(msgPlayer1.playerColor));
			player1NameLabel.setText(msgPlayer1.playerName);
		}
		player1Stickman.draw(spriteBatch);
		
		
		//TODO UNCOMMENT THIS FOR TWO PLAYERS
		ClientToServerMsg msgPlayer2 = NetworkManager.getInstance().getPlayer2().getLastMessage();
		if(msgPlayer2 != null && msgPlayer2.playerColor != null) {
			player2Stickman.setColor(TextureManager.getColorFromString(msgPlayer2.playerColor));
			player2NameLabel.setText(msgPlayer2.playerName);
		}
		player2Stickman.draw(spriteBatch);
		
	}
	
	private void createStickmenSprites() {
		player1Stickman = new Sprite(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.RIGHT).getKeyFrame(0));
		player1Stickman.setPosition(0.1f*WIDTH, 0);
		player1Stickman.setSize(0.4f*WIDTH, 0.7f*HEIGHT);
		player2Stickman = new Sprite(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.LEFT).getKeyFrame(0));
		player2Stickman.setPosition(0.5f*WIDTH, 0.05f*HEIGHT);
		player2Stickman.setSize(0.4f*WIDTH, 0.7f*HEIGHT);
	}
	
	

}
