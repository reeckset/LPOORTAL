package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.LpoortalGame.CONTROLLER_STATE;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.ServerToClientMsg;
import com.lpoortal.game.network.SocketCommunicator;

public class PlayerCustomizationController {
	
	private String gameCode = null;
	private LpoortalGame game;
	
	private boolean p1Ready = false;
	private boolean p2Ready = false;
	
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
		
		String p2Color = p2.getLastMessage().playerColor;
		String p1Color = p1.getLastMessage().playerColor;
		
		String p2Skin = p2.getLastMessage().playerSkin;
		String p1Skin = p1.getLastMessage().playerSkin;
		
		if (p1Color.equals(p2Color)){
			p1.changeState(LpoortalGame.CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE);
			p2.changeState(LpoortalGame.CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE);
			p1Ready = false;
			p2Ready = false;
			NetworkManager.getInstance().getPlayer1().resetLastMessage();
			NetworkManager.getInstance().getPlayer2().resetLastMessage();
		} else {
			
			if(CONTROLLER_STATE.valueOf(p1.getLastMessage().controllerState) == CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE &&
			   CONTROLLER_STATE.valueOf(p2.getLastMessage().controllerState) == CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE )
				
				
			
			p1.setColor(p1Color);
			p2.setColor(p2Color);
			
			p1.setSkin(p1Skin);
			p2.setSkin(p2Skin);
			
			p1.changeState(LpoortalGame.CONTROLLER_STATE.DRAWING_STATE);
			p2.changeState(LpoortalGame.CONTROLLER_STATE.MOVEMENT_STATE);
			
			game.setState(LpoortalGame.STATE.GAMEPLAY);
		}
		
	}
	
	public SocketCommunicator getPlayer1() {
		return NetworkManager.getInstance().getPlayer1();
	}
	
	public SocketCommunicator getPlayer2() {
		return NetworkManager.getInstance().getPlayer2();
	}


	public void update() {
		ClientToServerMsg player1Msg = NetworkManager.getInstance().getPlayer1().getLastMessage();
		ClientToServerMsg player2Msg = NetworkManager.getInstance().getPlayer2().getLastMessage();
		
		if(!p1Ready && player1Msg != null && player1Msg.actionBtn) {
			p1Ready = true;
			NetworkManager.getInstance().getPlayer1().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
		}
		if(!p2Ready && player2Msg != null && player2Msg.actionBtn) {
			p2Ready = true;
			NetworkManager.getInstance().getPlayer2().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
		}
		if(p1Ready && p2Ready) {
			nextState();
		}
		
		
	}
}