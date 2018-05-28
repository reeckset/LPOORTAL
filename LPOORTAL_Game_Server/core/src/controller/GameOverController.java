package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;

public class GameOverController {
	private LpoortalGame game;
	private int finalScore;
	
	public GameOverController(LpoortalGame game) {
		this.game = game;
		this.finalScore = GameController.getInstance().getScore();
	}

	public void update() {
		
		
	}
	
	public int getScore() {
		return this.finalScore;
	}
	
	
}
