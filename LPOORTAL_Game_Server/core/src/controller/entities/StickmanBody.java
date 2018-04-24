package controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.StickmanModel;

public class StickmanBody extends EntityBody {

	public StickmanBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.DynamicBody);
		
		float density = 1f, friction = 0.8f, restitution = 0f;
        float width = 2f, height = 4f;

        PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(width/2,
	    					  height / 2,
	    					  new Vector2(0,-0.5f),
	    					  0);
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.restitution = restitution;
	    def.density = density;
	    def.friction = friction;
	    body.setFixedRotation(true);  
	    body.createFixture(def);
	}

}


