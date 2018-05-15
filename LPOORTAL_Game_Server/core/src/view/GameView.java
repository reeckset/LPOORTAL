package view;

import com.badlogic.gdx.ScreenAdapter;
import com.lpoortal.game.LpoortalGame;

public class GameView {
	
	public static GameView instance;
	
	private GameView(){
		instance = this;
	}
	
	public static GameView getInstance() {
		if (instance == null)
			instance = new GameView();
		return instance;
	}
}
