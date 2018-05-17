package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.ServerToClientMsg;
import com.lpoortal.game.network.SocketCommunicator;

public class PlayerCustomizationController {
	
	private String gameCode = null;
	private LpoortalGame game;
	
	public PlayerCustomizationController(LpoortalGame game) {
		this.game = game;
		assignPlayerColors();
	}
	
	
	private void assignPlayerColors() {
		
	}
	
	public String getGameCode() {
		return gameCode;
	}
	
	public void nextState() {
		
		SocketCommunicator p1 = NetworkManager.getInstance().getPlayer1();
		SocketCommunicator p2 = NetworkManager.getInstance().getPlayer2();
		
		p1.changeState(LpoortalGame.CONTROLLER_STATE.DRAWING_STATE);
		p2.changeState(LpoortalGame.CONTROLLER_STATE.MOVEMENT_STATE);
		
		game.setState(LpoortalGame.STATE.GAMEPLAY);
		
		
	}
}