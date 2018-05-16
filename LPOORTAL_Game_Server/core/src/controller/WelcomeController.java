package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;

import view.entities.LevelScreen;

public class WelcomeController {
	
	private String gameCode = null;
	private LpoortalGame game;
	
	public WelcomeController(LpoortalGame game) {
		this.game = game;
		generateGameCode();
	}
	
	private void generateGameCode() {
		String ip;
		while((ip = NetworkManager.getInstance().getHostIp()) == null);
		String[] values = ip.split("\\.");
		String tmpGameCode = "";
		for(String value : values) {
			tmpGameCode += String.format("%02x", Integer.parseInt(value));
		}
		this.gameCode = tmpGameCode.toUpperCase();
	}
	
	public String getGameCode() {
		return gameCode;
	}
	
	public void nextState() {
		game.setState(LpoortalGame.STATE.GAMEPLAY);
	}
}
