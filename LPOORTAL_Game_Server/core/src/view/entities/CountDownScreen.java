package view.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.LpoortalGame.STATE;

public class CountDownScreen extends GUIScreen {

	float time = 3f;
	Integer countDown = 3;
	Label label;
	
	
    public CountDownScreen() {
        super();
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
