package model.entities;

public class InkJarModel extends EntityModel {

	/**
	 * 
	 * @param x - x starting position
	 * @param y - y starting position
	 */
	public InkJarModel(float  x, float  y) {
		super(x, y);
	}
	
	@Override
	public ModelType getType() {
		return ModelType.INKJAR;
	}
}
