package model.entities;

public class DrawnLineModel extends EntityModel {

	public static final float THICKNESS = 0.2f;
	private float xi, yi, xf, yf;
	private float xLength, yLength, length, angle;
	private boolean isPreview = true;

	/**
	 * 
	 * @param xi - line starting x
	 * @param yi - line starting y
	 * @param xf - line ending x
	 * @param yf - line ending y
	 */
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
	
	/**
	 * 
	 * @return ending x
	 */
	public float getXf() {
		return xf;
	}
	
	/**
	 * 
	 * @return ending y
	 */
	public float getYf() {
		return yf;
	}
	
	/**
	 * 
	 * @return starting x
	 */
	public float getXi() {
		return xi;
	}
	
	/**
	 * 
	 * @return starting y
	 */
	public float getYi() {
		return yi;
	}
	
	/**
	 * 
	 * @return the horizontal length
	 */
	public float getXLength() {
		return xLength;
	}

	/**
	 * 
	 * @return the vertical length
	 */
	public float getYLength() {
		return yLength;
	}

	/**
	 * 
	 * @return line length
	 */
	public float getLength() {
		return length;
	}
	
	/**
	 * 
	 * @return angle the line makes with the horizontal axis
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * 
	 * @return true if the line is a preview line
	 */
	public boolean isPreview() {
		return this.isPreview;
	}
	
	
	/**
	 * Makes the line definitive
	 */
	public void setDefinitive() {
		this.isPreview = false;
	}

	
}
