package model.entities;

import com.badlogic.gdx.graphics.Color;

public class CursorModel extends EntityModel {
	
	public final static float THICKNESS = 0.02f;
	
	private Color color;

	/**
	 * 
	 * @param x - cursor x starting position
	 * @param y - cursor y starting position
	 */
	public CursorModel(float x, float y) {
		super(x, y);
	}

	@Override
	public ModelType getType() {
		return ModelType.CURSOR;
	}

	/**
	 * 
	 * @param dx - movement on the x axis
	 * @param dy - movement on the y axis
	 */
	public void move(float dx, float dy) {
		this.x = dx;
		this.y = dy;
	}

	/**
	 * 
	 * @param color - the cursor color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return the cursor color
	 */
	public Color getColor() {
		return this.color;
	}
	

}
