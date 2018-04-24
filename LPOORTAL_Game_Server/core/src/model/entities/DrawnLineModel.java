package model.entities;

public class DrawnLineModel extends EntityModel {

	public static final float THICKNESS = 0.2f;
	private float xi, yi, xf, yf;
	private float xLength, yLength, length, angle;

	public DrawnLineModel(float  xi, float  yi, float  xf, float  yf) {
		super((xi+xf)/2, (yi+yf)/2);
		this.xf = xf;
		this.yf = yf;
		this.xi = xi;
		this.yi = yi;
		this.xLength = Math.abs(xi-xf);
		this.yLength = Math.abs(yi-yf);
		this.length = (float) Math.sqrt(xLength*xLength+yLength*yLength);
        this.angle = (float) Math.acos(xLength/length);
        if((yi-yf)*(xi-xf) < 0) this.angle = -this.angle;
	}
	
	@Override
	public ModelType getType() {
		return ModelType.LINE;
	}
	
	public float getXf() {
		return xf;
	}
	
	public float getYf() {
		return yf;
	}
	
	public float getXi() {
		return xi;
	}
	
	public float getYi() {
		return yi;
	}
	
	public float getXLength() {
		return xLength;
	}

	public float getYLength() {
		return yLength;
	}

	public float getLength() {
		return length;
	}
	
	public float getAngle() {
		return angle;
	}

	
}
