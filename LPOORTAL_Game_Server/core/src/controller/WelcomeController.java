package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.ServerToClientMsg;
import com.lpoortal.game.network.SocketCommunicator;

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
		
		SocketCommunicator p1 = NetworkManager.getInstance().getPlayer1();
		SocketCommunicator p2 = NetworkManager.getInstance().getPlayer2();
		
		//Mandar msgs aos players
		ServerToClientMsg msg1 = new ServerToClientMsg(LpoortalGame.CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE.toString());
		ServerToClientMsg msg2 = new ServerToClientMsg(LpoortalGame.CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE.toString());
		p1.writeMsg(msg1);
		//p2.writeMsg(msg2);
		
		game.setState(LpoortalGame.STATE.PLAYER_CUSTOMIZATION);
		
		
	}
}
