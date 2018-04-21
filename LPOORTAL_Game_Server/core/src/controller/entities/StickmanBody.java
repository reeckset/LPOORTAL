package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.EntityModel;

public class StickmanBody extends EntityBody {

	public StickmanBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.DynamicBody);
	}

}
