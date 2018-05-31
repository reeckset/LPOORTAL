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

import controller.GameOverController;
import controller.WelcomeController;
import view.entities.TextureManager.GUI_Texture;

public class GameOverScreen extends GUIScreen {

	GameOverController controller;
	
	Image player1Connection;
	Image player2Connection;
	
	SpriteDrawable tickDrawable;
	SpriteDrawable crossDrawable;

	
    public GameOverScreen(LpoortalGame game) {
        super(game);
        controller = new GameOverController(game);
        createUI();
    }
    
    @Override
    public void render(float delta) {
    	super.render(delta);
    	controller.update();
    }


	
	private void createUI(){
    	
    	showImage(textureManager.getGUITexture(GUI_Texture.LOGO), 400, 260, 200, 100);
    	showGameOverLabel();
    	
    	showScore();
    }
 
 

	private void showGameOverLabel() {
       showLabel("Game Over", 0, 260, 420, 100);
     
	}

	private void showScore() {
		showLabel("Score: " + this.controller.getScore(), 0, 0, 420, 100);
    }
    

}
