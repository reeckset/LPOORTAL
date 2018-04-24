package model.entities;

import utilities.Pair;

public class StickmanModel extends EntityModel {

	public enum Stickman_Animation {WALKING, IDLE, JUMPING, LIFT_OFF, LANDING};
	public enum Stickman_Facing_Direction {RIGHT, LEFT};
	
	private Stickman_Animation stickmanState;
	private Stickman_Facing_Direction stickmanFacingDirection;
	
	
	public StickmanModel(float x, float y) {
		super(x, y);
		stickmanState = Stickman_Animation.IDLE;
		stickmanFacingDirection = Stickman_Facing_Direction.RIGHT;
	}

	public boolean isJumping() {
		return stickmanState == Stickman_Animation.JUMPING;
	}

	public void setJumping(boolean jumping) {
		if(jumping) {
			this.stickmanState = Stickman_Animation.JUMPING;
		}else {
			this.stickmanState = Stickman_Animation.IDLE;
		}
	}

	@Override
	public ModelType getType() {
		return ModelType.STICKMAN;
	}

	public Stickman_Animation getState() {
		return this.stickmanState;
	}
	
	public Stickman_Facing_Direction getFacingDirection() {
		return this.stickmanFacingDirection;
	}

	public void setState(Stickman_Animation state) {
		this.stickmanState = state;
		
	}
}
