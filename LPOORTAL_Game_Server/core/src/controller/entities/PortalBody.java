package controller.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.PortalModel;
import model.entities.StickmanModel;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;

public class PortalBody extends EntityBody {
	

	public static float WIDTH = 24f;
	public static float HEIGHT = 4f;
	
	
	/**
	 * Portal Body Constructor
	 * @param world - the game's world
	 * @param model - the portal model
	 */
	public PortalBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.StaticBody);
	    
	    PolygonShape sensorShape = new PolygonShape();
	    sensorShape.setAsBox(WIDTH/2.3f,
    					  HEIGHT/8,
    					  new Vector2(0,0),
    					  0);
	    FixtureDef sensor = new FixtureDef();
	    sensor.shape = sensorShape;
	    sensor.isSensor = true;
	    body.createFixture(sensor);
	    
	}
	
}


