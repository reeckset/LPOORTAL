package model.entities;

public class CursorModel extends EntityModel {

	public CursorModel(float x, float y) {
		super(x, y);
	}

	@Override
	public ModelType getType() {
		return ModelType.CURSOR;
	}

}