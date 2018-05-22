package model.entities;

import com.badlogic.gdx.graphics.Color;

import utilities.Pair;
import view.entities.StickmanVisualDetails.Stickman_Skin;
import view.entities.TextureManager;

public class StickmanModel extends EntityModel {

	public enum Stickman_Animation {WALKING, IDLE, JUMPING, LIFT_OFF, LANDING};
	public enum Stickman_Facing_Direction {RIGHT, LEFT};
	
	private Stickman_Animation stickmanState;
	private Stickman_Facing_Direction stickmanFacingDirection;
	
	private Color color;
	private Stickman_Skin skin;

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
			this.stickmanState = Stickman_Animation.LANDING;
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

	public void setFacingDirection(Stickman_Facing_Direction direction) {
		this.stickmanFacingDirection = direction;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Stickman_Skin getSkin() {
		return skin;
	}

	public void setSkin(Stickman_Skin skin) {
		this.skin = skin;
	}
	
	
}
