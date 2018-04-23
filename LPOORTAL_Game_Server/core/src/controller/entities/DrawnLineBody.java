package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.EntityModel;

public class DrawnLineBody extends EntityBody {

	public DrawnLineBody(World world, EntityModel model) {
		super(world, model,BodyDef.BodyType.StaticBody);
	
	
		float density = 0.5f, friction = 0.4f, restitution = 0.5f;
	    int width = 600, height = 50;
	
	    // Body
	    createFixture(body, new float[]{
	            0,0, 0,50, 600,50, 600,0 
	    }, width, height, density, friction, restitution, DRAWNLINE_BODY, (short) (STICKMAN_BODY | DRAWNLINE_BODY | CURSOR_BODY));
	}

}
