package model.entities;

import com.badlogic.gdx.graphics.Color;

public class PortalModel extends EntityModel {

	Color color;
	
	/**
	 * 
	 * @param x - x starting position
	 * @param y - y starting position
	 */
	public PortalModel(float  x, float  y) {
		super(x,y);
		
	}
	
	@Override
	public ModelType getType() {
		return ModelType.PORTAL;
	}
	
	/**
	 * 
	 * @return the portal color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param color - the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
