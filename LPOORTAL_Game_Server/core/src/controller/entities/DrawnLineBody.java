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
		
		float xLength = lineModel.getX()-lineModel.getXf();
        float yLength = lineModel.getY()-lineModel.getYf();
        float length = (float) Math.sqrt(xLength*xLength+yLength*yLength);
        
        Vector2 center = new Vector2(0,0);
		
        float angle = (float) Math.acos(xLength/length);
        if(yLength < 0) angle = -angle;
        
	    PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(Math.abs(xLength)/2,
	    		0.01f,
	    						center,
	    						angle);
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.restitution = 0;
	    def.density = 1000;
	    def.friction = 1000;
	    
	    body.createFixture(def);
	}

}
