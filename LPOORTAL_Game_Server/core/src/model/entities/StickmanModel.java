package model.entities;

public class StickmanModel extends EntityModel {

	boolean jumping = false;
	
	public StickmanModel(float x, float y) {
		super(x, y);
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean value) {
		this.jumping = value;
	}

}
