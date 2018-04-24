package controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;

public class DrawnLineBody extends EntityBody {

	public DrawnLineBody(World world, EntityModel model) {
		super(world, model,BodyDef.BodyType.StaticBody);
	    
		DrawnLineModel lineModel = (DrawnLineModel) model;
        
	    PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(lineModel.getXLength()/2,
	    					  DrawnLineModel.THICKNESS / 2,
	    					  new Vector2(0,0),
	    					  lineModel.getAngle());
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.restitution = 0;
	    def.density = 1000;
	    def.friction = 1000;
	    
	    body.createFixture(def);
	}

}
