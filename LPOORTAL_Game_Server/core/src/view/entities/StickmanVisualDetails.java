package view.entities;

import model.entities.StickmanModel;

public class StickmanVisualDetails{
	
	public enum Stickman_Skin {COWBOY, SPACEMAN, NINJA, CHEF, LOL};
	
	StickmanModel.Stickman_Animation animation;
	StickmanModel.Stickman_Facing_Direction direction;
	Stickman_Skin skin;
	
	/**
	 * 
	 * @param animation - the animation
	 * @param direction - the facing direction
	 * @param skin - the stickman skin
	 */
	public StickmanVisualDetails(StickmanModel.Stickman_Animation animation, StickmanModel.Stickman_Facing_Direction direction, Stickman_Skin skin) {
		this.animation = animation;
		this.direction = direction;
		this.skin = skin;
	}

	/**
	 * 
	 * @return the stickman animation
	 */
	public StickmanModel.Stickman_Animation getAnimation() {
		return animation;
	}

	/**
	 * 
	 * @return the stickman facing direction
	 */
	public StickmanModel.Stickman_Facing_Direction getDirection() {
		return direction;
	}

	/**
	 * 
	 * @return the stickman skin
	 */
	public Stickman_Skin getSkin() {
		return skin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((animation == null) ? 0 : animation.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((skin == null) ? 0 : skin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StickmanVisualDetails other = (StickmanVisualDetails) obj;
		if (animation != other.animation)
			return false;
		if (direction != other.direction)
			return false;
		if (skin != other.skin)
			return false;
		return true;
	}

	
	
	
}
