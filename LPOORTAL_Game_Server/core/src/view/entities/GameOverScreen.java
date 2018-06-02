package view.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import controller.GameOverController;
import view.entities.TextureManager.GUI_Texture;

public class GameOverScreen extends GUIScreen {

	GameOverController controller;
	
	Image player1Connection;
	Image player2Connection;
	
	SpriteDrawable tickDrawable;
	SpriteDrawable crossDrawable;

	
	/**
	 * Creates a GameOver Screen
	 */
    public GameOverScreen() {
        super();
        controller = new GameOverController();
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
