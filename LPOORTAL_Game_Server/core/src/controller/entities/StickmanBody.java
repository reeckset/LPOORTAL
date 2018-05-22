package controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.StickmanModel;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;

public class StickmanBody extends EntityBody {
	
	private static final float FRICTION = 0.8f;
	private static final float RESTITUTION = 0f;
	private static final float DENSITY = 1f;
	public static final float WIDTH = 5f, HEIGHT = 5f;
	private static final float FACING_DIRECTION_THRESHOLD = 0.2f;
	private int nbrCollidingObjects = 0;

	public StickmanBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.DynamicBody);
	
        

        PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(WIDTH/8,
	    					  HEIGHT / 3,
	    					  new Vector2(0,-0.7f),
	    					  0);
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.restitution = 0;
	    def.density = 0;
	    def.friction = 0;
	    body.setFixedRotation(true);  
	    body.createFixture(def);
	    
	    PolygonShape sensorShape = new PolygonShape();
	    sensorShape.setAsBox(WIDTH/8,
    					  HEIGHT / 300,
    					  new Vector2(0,-HEIGHT/2),
    					  0);
	    FixtureDef bottomDef = new FixtureDef();
	    bottomDef.restitution = RESTITUTION;
	    bottomDef.density = DENSITY;
	    bottomDef.friction = FRICTION;
	    bottomDef.shape = sensorShape;
	    body.createFixture(bottomDef);
	}
	
	public void update() {
		StickmanModel model = ((StickmanModel) this.getUserData());

		if(!model.isJumping()) {
			if(getSpeedX() != 0) {
				model.setState(Stickman_Animation.WALKING);
			}else {
				model.setState(Stickman_Animation.IDLE);
			}
		}
		
		updateFacingDirection();
	}
	
	private void updateFacingDirection() {
		if(this.getSpeedX() > FACING_DIRECTION_THRESHOLD) {
			faceRight();
		}else if(this.getSpeedX() < FACING_DIRECTION_THRESHOLD) {
			faceLeft();
		}
	}

	public void faceLeft() {
		StickmanModel model = ((StickmanModel) this.getUserData());
		model.setFacingDirection(Stickman_Facing_Direction.LEFT);
	}

	public void faceRight() {
		StickmanModel model = ((StickmanModel) this.getUserData());
		model.setFacingDirection(Stickman_Facing_Direction.RIGHT);
	}
	
	public void increaseNbrCollidedObjs() {
		nbrCollidingObjects++;
	}
	public void decreaseNbrCollidedObjs() {
		nbrCollidingObjects--;
	}
	
	public boolean isColliding() {
		return nbrCollidingObjects > 0;
	}
}


