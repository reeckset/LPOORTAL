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
		
		//Mandar msgs aos players
		ServerToClientMsg msg1 = new ServerToClientMsg(LpoortalGame.CONTROLLER_STATE.DRAWING_STATE.toString());
		ServerToClientMsg msg2 = new ServerToClientMsg(LpoortalGame.CONTROLLER_STATE.MOVEMENT_STATE.toString());
		p1.writeMsg(msg1);
		//p2.writeMsg(msg2);
		
		game.setState(LpoortalGame.STATE.GAMEPLAY);
		
		
	}
}