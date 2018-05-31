package controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;



public class DrawnLineBody extends EntityBody {
	
	private FixtureDef def;
	
	/**
	 * Drawn Line Body Constructor
	 * @param world - the game's world
	 * @param model - the line model
	 */
	public DrawnLineBody(World world, EntityModel model) {
		super(world, model,BodyDef.BodyType.StaticBody);
	    
		DrawnLineModel lineModel = (DrawnLineModel) model;
        
	    PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(lineModel.getLength()/2,
	    					  DrawnLineModel.THICKNESS / 2,
	    					  new Vector2(0,0),
	    					  lineModel.getAngle());
	    
	    def = new FixtureDef();
	    def.shape = polygonShape;
	    def.restitution = 0;
	    def.density = 1000;
	    def.friction = 1f;
	    
	}
	
	/**
	 * Sets this line as definitive (activates collisions)
	 */
	public void setDefinitive() {
		if(((DrawnLineModel)getUserData()).isPreview()) {
		    body.createFixture(def);
		    ((DrawnLineModel)getUserData()).setDefinitive();
		}
	}

}
