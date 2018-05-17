package view.entities;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;

import controller.WelcomeController;
import view.entities.TextureManager.GUI_Texture;

public class WelcomeScreen extends GUIScreen {

	WelcomeController controller;
	
	Image player1Connection;
	Image player2Connection;
	
	SpriteDrawable tickDrawable;
	SpriteDrawable crossDrawable;
	
	private boolean p1Ready = false;
	private boolean p2Ready = false;
	private boolean p1Closed = false;
	private boolean p2Closed = false;

	
    public WelcomeScreen(LpoortalGame game) {
        super(game);
        controller = new WelcomeController(game);
        createUI();
    }
    
    @Override
    public void render(float delta) {
    	super.render(delta);
    	controller.update();
    	
		if(NetworkManager.getInstance().isPlayer1Connected()) {
    		showPlayer1Connected();
    	} else {
    		showPlayer1Disconnected();
    	}
		if(NetworkManager.getInstance().isPlayer2Connected()) {
    		showPlayer2Connected();
    	} else {
    		showPlayer2Disconnected();
    	}
    	
    	
    }

    private void showPlayer2Connected() {
    	player2Connection.setDrawable(tickDrawable);		
	}

	private void showPlayer1Connected() {
		player1Connection.setDrawable(tickDrawable);
	}
	
	
	private void showPlayer1Disconnected() {
		player1Connection.setDrawable(crossDrawable);
	}
	
	private void showPlayer2Disconnected() {
    	player2Connection.setDrawable(crossDrawable);		
	}

	
	private void createUI(){
    	showGameCode();
    	showImage(GUI_Texture.LOGO, 400, 260, 200, 100);
    	showWelcomeLabel();
    	showGameCodeInstruction();
    	showPlayersConnectionInfo();
    }
 
    private void showPlayersConnectionInfo() {
		showSubText("Player 1 Connection Status:", 10, 30, 100, 30);
		showSubText("Player 2 Connection Status:", 10, 0, 100, 30);
		player1Connection = showImage(GUI_Texture.CROSS, 230, 30, 25, 25);
		player2Connection = showImage(GUI_Texture.CROSS, 235, 0, 25, 25);
		tickDrawable = new SpriteDrawable(new Sprite(textureManager.getGUITexture(GUI_Texture.TICK)));
		crossDrawable = new SpriteDrawable(new Sprite(textureManager.getGUITexture(GUI_Texture.CROSS)));
	}

	private void showGameCodeInstruction() {
    	Label label = new Label("Please insert this \ngamecode in your device:", textureManager.getSubTextStyle());
        stage.addActor(label);
        label.setPosition(0, 30);
        label.setSize(VP_WIDTH/2, VP_HEIGHT);
        label.setAlignment(Align.center);
    }

	private void showWelcomeLabel() {
       Label label = new Label("Welcome to", textureManager.getLabelStyle());
       stage.addActor(label);
       label.setPosition(0, 260);
       label.setSize(420, 100);
       label.setAlignment(Align.center);
     
	}

	private void showGameCode() {
   	 Label ipLabel = new Label(controller.getGameCode(), textureManager.getLabelStyle());
        stage.addActor(ipLabel);
        ipLabel.setPosition(0, -30);
        ipLabel.setSize(VP_WIDTH/2, VP_HEIGHT);
        ipLabel.setAlignment(Align.center);
    }
    

}
