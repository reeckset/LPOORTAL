package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import controller.GameController;
import model.entities.EntityModel;

public class CursorBody extends EntityBody {
	
	private static final float GYRO_SENSITIVITY_X = 0.2f;
	private static final float GYRO_SENSITIVITY_Y = 0.1f;
	
	private float lastX = 0;
	private float lastY = 0;
	
	/**
	 * Cursor Body Constructor
	 * @param world - the game's world
	 * @param model - the cursor model
	 */
	public CursorBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.StaticBody);
	}
	
	/**
	 * Updates the cursor position
	 * @param inputX - the next x position
	 * @param inputY - the next y position
	 */
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
