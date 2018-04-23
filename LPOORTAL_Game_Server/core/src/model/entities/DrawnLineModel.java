package model.entities;

public class DrawnLineModel extends EntityModel {

	private int xf, yf;
	
	public DrawnLineModel(int xi, int yi, int xf, int yf) {
		super(xi, yi);
		this.xf = xf;
		this.yf = yf;
	}

	@Override
	public ModelType getType() {
		return ModelType.LINE;
	}
	
	public int getXf() {
		return xf;
	}
	
	public int getYf() {
		return yf;
	}
	
}
