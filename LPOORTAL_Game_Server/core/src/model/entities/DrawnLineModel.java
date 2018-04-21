package model.entities;

public class DrawnLineModel extends EntityModel {

	private int xf, yf;
	
	public DrawnLineModel(int xi, int yi, int xf, int yf) {
		super(xi, yi);
		this.xf = xf;
		this.yf = yf;
	}
	
}
