package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.ServerToClientMsg;
import com.lpoortal.game.network.PlayerSocket;

public class WelcomeController {
	
	private String gameCode = null;
	private LpoortalGame game;
	
	private boolean isPlayer1Ready = false;
	private boolean isPlayer2Ready = false;
	
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
	
	/**
	 * 
	 * @return the game code necessary for connection 
	 */
	public String getGameCode() {
		return gameCode;
	}
	
	/**
	 * Advances for next state if the conditions are verified
	 */
	public void nextState() {
		
		PlayerSocket p1 = NetworkManager.getInstance().getPlayer1();
		PlayerSocket p2 = NetworkManager.getInstance().getPlayer2();
		
		p1.changeState(LpoortalGame.CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE);
		p2.changeState(LpoortalGame.CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE);
		
		game.setState(LpoortalGame.STATE.PLAYER_CUSTOMIZATION);
		
		
	}

	/**
	 * 
	 * @return player 1 SocketCommunicator
	 */
	public PlayerSocket getPlayer1() {
		return NetworkManager.getInstance().getPlayer1();
	}

	/**
	 * 
	 * @return player 2 SocketCommunicator
	 */
	public PlayerSocket getPlayer2() {
		return NetworkManager.getInstance().getPlayer2();
	}
	
	/**
	 * Updates the game state. Called every frame
	 */
	public void update() {

		if(NetworkManager.getInstance().isPlayer1Connected() && !isPlayer1Ready) {
			this.getPlayer1().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
			isPlayer1Ready = true;
		}
		if(NetworkManager.getInstance().isPlayer2Connected() && !isPlayer2Ready) {
			this.getPlayer2().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
			isPlayer2Ready = true;
		}
		
    	if(NetworkManager.getInstance().isPlayer1Connected() && NetworkManager.getInstance().isPlayer2Connected()) {
    		nextState();
    	}
	}
}
