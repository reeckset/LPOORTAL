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

	/**
	 * 
	 * @param x - x starting position
	 * @param y - y starting position
	 */
	public StickmanModel(float x, float y) {
		super(x, y);
		stickmanState = Stickman_Animation.IDLE;
		stickmanFacingDirection = Stickman_Facing_Direction.RIGHT;
	}

	/**
	 * 
	 * @return true if the stickman is jumping
	 */
	public boolean isJumping() {
		return stickmanState == Stickman_Animation.JUMPING;
	}

	/**
	 * 
	 * @param jumping - true/false if the stickman is jumping or not
	 */
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

	/**
	 * 
	 * @return the stickman animation state
	 */
	public Stickman_Animation getState() {
		return this.stickmanState;
	}
	
	/**
	 * 
	 * @return the stickman facing direction
	 */
	public Stickman_Facing_Direction getFacingDirection() {
		return this.stickmanFacingDirection;
	}

	/**
	 * 
	 * @param state - the stickman animation state
	 */
	public void setState(Stickman_Animation state) {
		this.stickmanState = state;
		
	}

	
	/**
	 * 
	 * @param direction - the stickman facing direction
	 */
	public void setFacingDirection(Stickman_Facing_Direction direction) {
		this.stickmanFacingDirection = direction;
	}
	
	
	/**
	 * 
	 * @param color the stickman color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return the stickman color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * 
	 * @return the stickman skin
	 */
	public Stickman_Skin getSkin() {
		return skin;
	}

	/**
	 * 
	 * @param skin - the stickman skin
	 */
	public void setSkin(Stickman_Skin skin) {
		this.skin = skin;
	}
	
	
}
