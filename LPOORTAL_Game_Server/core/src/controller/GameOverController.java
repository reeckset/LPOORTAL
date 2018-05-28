package controller;

import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.LpoortalGame.CONTROLLER_STATE;
import com.lpoortal.game.LpoortalGame.STATE;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;

public class GameOverController {
	private LpoortalGame game;
	private int finalScore;
	
	public GameOverController(LpoortalGame game) {
		this.game = game;
		this.finalScore = GameController.getInstance().getScore();
		NetworkManager.getInstance().getPlayer1().changeState(LpoortalGame.CONTROLLER_STATE.GAME_OVER_STATE);
		NetworkManager.getInstance().getPlayer2().changeState(LpoortalGame.CONTROLLER_STATE.GAME_OVER_STATE);
	}

	public void update() {
		
		if(NetworkManager.getInstance().getPlayer1() == null || NetworkManager.getInstance().getPlayer2() == null) {
			LpoortalGame.getInstance().setState(STATE.WELCOME);
		}
		
		ClientToServerMsg msg1 = NetworkManager.getInstance().getPlayer1().getLastMessage();
		ClientToServerMsg msg2 = NetworkManager.getInstance().getPlayer2().getLastMessage();
		
		
		if(msg1 != null && msg2 != null) {
			if(msg1.actionBtn && CONTROLLER_STATE.valueOf(msg1.controllerState) == CONTROLLER_STATE.GAME_OVER_STATE ||
			   msg2.actionBtn && CONTROLLER_STATE.valueOf(msg2.controllerState) == CONTROLLER_STATE.GAME_OVER_STATE) {
				LpoortalGame.getInstance().setState(STATE.PLAYER_CUSTOMIZATION);
				GameController.getInstance().resetGame();
				
				NetworkManager.getInstance().getPlayer1().changeState(CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE);
				NetworkManager.getInstance().getPlayer2().changeState(CONTROLLER_STATE.PLAYER_CUSTOMIZATION_STATE);
				
			}
		}
		
	}
	
	public int getScore() {
		return this.finalScore;
	}
	
	
}
