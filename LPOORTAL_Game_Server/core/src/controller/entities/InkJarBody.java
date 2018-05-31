package controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.InkJarModel;

public class InkJarBody extends EntityBody {
	
	public static final float WIDTH = 2f;
	public static final float HEIGHT = 2f;
	
	/**
	 * Ink Jar Body Constructor
	 * @param world - the game's world
	 * @param model - the ink jar model
	 */
	public InkJarBody(World world, InkJarModel model) {
		super(world, model,BodyDef.BodyType.StaticBody);
        
	    PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(WIDTH/2,
	    					  HEIGHT/2,
	    					  new Vector2(0,0),
	    					  0);
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.restitution = 0;
	    def.density = 1000;
	    def.friction = 1f;
	    def.isSensor = true;
	    body.createFixture(def);
	}

}
