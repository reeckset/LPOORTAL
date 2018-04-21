package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.EntityModel;

public class DrawnLineBody extends EntityBody {

	public DrawnLineBody(World world, EntityModel model) {
		super(world, model,BodyDef.BodyType.StaticBody);
	}

}
