package model.entities;

public class CursorModel extends EntityModel {
	
	public final static float THICKNESS = 0.02f;

	public CursorModel(float x, float y) {
		super(x, y);
	}

	@Override
	public ModelType getType() {
		return ModelType.CURSOR;
	}

	public void move(float dx, float dy) {
		this.x = dx;
		this.y = dy;
	}

}
