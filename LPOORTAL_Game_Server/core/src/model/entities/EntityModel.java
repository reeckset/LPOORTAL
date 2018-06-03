package model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class EntityModel {
	protected float x;

	protected float y;

	private boolean flaggedForRemoval = false;

	public enum ModelType {STICKMAN, CURSOR, LINE, PORTAL, INKJAR};
	
	/**
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 */
	public EntityModel(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	 /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }
	
	/**
     * Sets the position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns if this entity has been flagged for removal
     *
     * @return true if flagged to be removed
     */
    public boolean isFlaggedToBeRemoved() {
        return flaggedForRemoval;
    }

    /**
     * Makes this model flagged for removal on next step
     * @param flaggedForRemoval true/false
     */
    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }
    
    /**
     * 
     * @return The model type
     */
    public abstract ModelType getType();
}
