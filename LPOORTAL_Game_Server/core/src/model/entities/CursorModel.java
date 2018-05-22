package model.entities;

import com.badlogic.gdx.graphics.Color;

public class CursorModel extends EntityModel {
	
	public final static float THICKNESS = 0.02f;
	
	private Color color;

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

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	

}
