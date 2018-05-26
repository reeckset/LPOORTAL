package model.entities;

public class InkJarModel extends EntityModel {

	public InkJarModel(float  x, float  y) {
		super(x, y);
	}
	
	@Override
	public ModelType getType() {
		return ModelType.INKJAR;
	}
}
