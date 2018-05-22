package model.entities;

import com.badlogic.gdx.graphics.Color;

public class PortalModel extends EntityModel {

	Color color;
	
	public PortalModel(float  x, float  y) {
		super(x,y);
		
	}
	
	@Override
	public ModelType getType() {
		return ModelType.PORTAL;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
