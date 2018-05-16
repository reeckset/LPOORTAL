package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.PlayerClient;

public class PlayerCustomizationController {
	
	private String gameCode = null;
	private LpoortalGame game;
	
	public PlayerCustomizationController(LpoortalGame game) {
		this.game = game;
		assignPlayerColors();
	}
	
	
	private void assignPlayerColors() {
		PlayerClient p1 = NetworkManager.getInstance().getPlayer1();
		PlayerClient p2 = NetworkManager.getInstance().getPlayer2();
		
		//Mandar msgs aos players
	}
	
	public String getGameCode() {
		return gameCode;
	}
	
	public void nextState() {
		game.setState(LpoortalGame.STATE.GAMEPLAY);
	}
}