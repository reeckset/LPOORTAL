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
import com.lpoortal.game.LpoortalGame.STATE;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.ServerToClientMsg;
import com.lpoortal.game.network.SocketCommunicator;

import controller.PlayerCustomizationController;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;
import view.entities.TextureManager.GUI_Texture;

public class CountDownScreen extends GUIScreen {

	float time = 3f;
	Integer countDown = 3;
	Label label;
	
	
    public CountDownScreen(LpoortalGame game) {
        super(game);
        createUI();
    }
    
    private void createUI() {
		showImage(textureManager.getCountdownBackground(), 0, 0, VP_WIDTH, VP_HEIGHT);
		label = showLabel(countDown.toString(), VP_WIDTH - 120, 30, 30, 30);
	}

	@Override
    public void render(float delta) {
    	super.render(delta);
    	time -= delta;
    	if(time < countDown - 1) {
    		if(countDown == 0) {
    			countDown = 3;
    			game.setState(STATE.GAMEPLAY);
    		}
    		countDown--;
        	label.setText(countDown.toString());
    	}
    	
    }
	

}
