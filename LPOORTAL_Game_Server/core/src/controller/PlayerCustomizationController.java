package controller;

import com.lpoortal.game.LpoortalGame;
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
		
		p1.changeState(LpoortalGame.CONTROLLER_STATE.DRAWING_STATE);
		p2.changeState(LpoortalGame.CONTROLLER_STATE.MOVEMENT_STATE);
		
		game.setState(LpoortalGame.STATE.GAMEPLAY);
		
		
	}
	
	public SocketCommunicator getPlayer1() {
		return NetworkManager.getInstance().getPlayer1();
	}
	
	public SocketCommunicator getPlayer2() {
		return NetworkManager.getInstance().getPlayer2();
	}


	public void update() {
		if(NetworkManager.getInstance().getPlayer1() != null && !p1Ready) {
    		
    		if(!p2Ready) {
    			getPlayer1().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
    			p1Ready = true;
    		} else {
    			
    			verifyDifferentColors();
    			getPlayer1().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
    			p1Ready = true;
    			//Aqui pode ser chamado o nextState(), mas antes fzr a verificaçao das cores diferentes
    		}
			
    		
    	}
    	if(NetworkManager.getInstance().getPlayer2() != null && !p2Ready) {
    	
    		
    		if(!p1Ready) {
    			getPlayer2().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
    			p2Ready = true;
    		} else {
    			
    			verifyDifferentColors();
    			getPlayer2().changeState(LpoortalGame.CONTROLLER_STATE.READY_STATE);
    			p2Ready = true;
    			
    			//Aqui pode ser chamado o nextState(), mas antes fzr a verificaçao das cores diferentes
    		}
    			
    		
    	}
    	
    	if(NetworkManager.getInstance().getPlayer1() != null && NetworkManager.getInstance().getPlayer2() != null) {
    		nextState();
    	}
		
	}
}