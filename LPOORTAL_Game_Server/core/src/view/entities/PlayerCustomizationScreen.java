package view.entities;

import java.io.File;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;

import controller.PlayerCustomizationController;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;
import view.entities.TextureManager.GUI_Texture;

public class PlayerCustomizationScreen extends GUIScreen {

	PlayerCustomizationController controller;
	
	Sprite player1Stickman;
	Sprite player2Stickman;
	
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
    	updateStickmen();
    }

	private void createUI(){
    	showHeader();
    	showStickmen();
    }
	
	private void showHeader() {
		showImage(GUI_Texture.LOGO, 440, 260, 200, 100);
    	Label title = showSubText("Customize your Stickmen!", 20, 265, VP_WIDTH, 80);
    	title.setFontScale(0.4f);
	}
	
	private void showStickmen() {
		createStickmenSprites();
        showPlayer1Stickman();
        showPlayer2Stickman();
	}
	
	private void showPlayer1Stickman() {
		Image img = new Image(player1Stickman);
        img.setSize(200, 200);
        img.setPosition(40, 20);
        stage.addActor(img);
	}
	
	private void showPlayer2Stickman() {
		Image img = new Image(player2Stickman);
        img.setSize(200, 200);
        img.setPosition(380, 40);
        stage.addActor(img);
	}
	
	private void updateStickmen() {
		player1Stickman.setRegion(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.RIGHT).getKeyFrame(time, true));
		player2Stickman.setRegion(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.LEFT).getKeyFrame(time, true));
		//ClientToServerMsg msgPlayer1 = NetworkManager.getInstance().getPlayer1().getLastMessage();
		//if(msgPlayer1 != null) {
			//player1Stickman.setColor(20f/255f, 160f/255f , 1, 1);
			//TextureManager.getColorFromString(msgPlayer1.playerColor) -> ISTO RETORNA A COR A SER USADA EM setColor
		//}
		//TODO SET COLOR DOES NOT WORK!
	}
	
	private void createStickmenSprites() {
		player1Stickman = new Sprite(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.RIGHT).getKeyFrame(0));
		player2Stickman = new Sprite(textureManager.getStickmanAnimation(Stickman_Animation.IDLE, Stickman_Facing_Direction.LEFT).getKeyFrame(0));
	}

}
