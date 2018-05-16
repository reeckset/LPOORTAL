package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.PlayerClient;
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
		SocketCommunicator p1 = NetworkManager.getInstance().getPlayer1();
		SocketCommunicator p2 = NetworkManager.getInstance().getPlayer2();
		
		//Mandar msgs aos players
		ServerToClientMsg msg = new ServerToClientMsg("PILA");
		//p1.writeMsg(msg);
	}
	
	public String getGameCode() {
		return gameCode;
	}
	
	public void nextState() {
		game.setState(LpoortalGame.STATE.GAMEPLAY);
	}
}