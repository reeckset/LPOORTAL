package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.EntityModel;

public class StickmanBody extends EntityBody {

	public StickmanBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.DynamicBody);
		
		float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 75, height = 140;

        // Body
        createFixture(body, new float[]{
                0,0, 0,140, 75,140, 75,0 
        }, width, height, density, friction, restitution, STICKMAN_BODY, (short) (STICKMAN_BODY | DRAWNLINE_BODY | CURSOR_BODY));
    }
}


