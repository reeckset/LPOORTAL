package view.entities;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;

import controller.WelcomeController;
import view.entities.TextureManager.GUI_Texture;

public class WelcomeScreen extends GUIScreen {

	WelcomeController controller;
	
    public WelcomeScreen(LpoortalGame game) {
        super(game);
        controller = new WelcomeController(game);
        createUI();
    }
    
    @Override
    public void render(float delta) {
    	super.render(delta);
    	
    	if(NetworkManager.getInstance().getPlayer1() != null && NetworkManager.getInstance().getPlayer2() != null) {
    		controller.nextState();
    	}
    	
    }

    private void createUI(){
    	showGameCode();
    	showImage(GUI_Texture.LOGO, 400, 260, 200, 100);
    	showWelcomeLabel();
    	showGameCodeInstruction();
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
