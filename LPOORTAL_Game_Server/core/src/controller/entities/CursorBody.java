package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.PlayerClient;
import com.lpoortal.game.network.SocketCommunicator;

import controller.GameController;
import model.entities.CursorModel;
import model.entities.EntityModel;
import view.entities.CursorView;
import view.entities.LevelScreen;

public class CursorBody extends EntityBody {
	
	private static final float GYRO_SENSITIVITY_X = 0.2f;
	private static final float GYRO_SENSITIVITY_Y = 0.1f;
	
	private float lastX = 0;
	private float lastY = 0;
	
	public CursorBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.StaticBody);
	}
	
	public void updatePosition(float inputX, float inputY) {
		float dx = inputX - lastX;
		float dy = inputY - lastY;
		lastX = inputX;
		lastY = inputY;
		float x = GameController.limitBoundsX(this.getX() + dx * GYRO_SENSITIVITY_X);
		float y = GameController.limitBoundsY(this.getY() + dy * GYRO_SENSITIVITY_Y);
		setTransform(x, y, 0);
 
	}


}
