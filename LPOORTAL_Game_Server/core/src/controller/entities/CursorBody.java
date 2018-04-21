package controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import model.entities.EntityModel;

public class CursorBody extends EntityBody {

	public CursorBody(World world, EntityModel model) {
		super(world, model, BodyDef.BodyType.StaticBody);
	}

}
