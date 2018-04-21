package view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import model.entities.EntityModel;
import model.entities.StickmanModel;
import utilities.Pair;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;


/**
 * A view representing a space ship
 */
public class StickmanView extends EntityView {
    
    /**
     * The animation used when the stickman is idle/stopped (left)
     */
    private Animation<TextureRegion> idleLeftAnimation;

    
    /**
     * The animation used when the stickman is idle/stopped (right)
     */
    private Animation<TextureRegion> idleRightAnimation;
    
    
    /**
     * The animation used when the stickman is walking right
     */
    private Animation<TextureRegion> walkingRightAnimation;

    /**
     * The animation used when the stickman is walking left
     */
    private Animation<TextureRegion> walkingLeftAnimation;
    
    /**
     * The animation used when the stickman is lifting_off left
     */
    private Animation<TextureRegion> liftOffLeftAnimation;


    /**
     * The animation used when the stickman is lifting_off right
     */
    private Animation<TextureRegion> liftOffRightAnimation;    


    /**
     * The animation used when the stickman is landing left
     */
    private Animation<TextureRegion> landingLeftAnimation;
    
    
    /**
     * The animation used when the stickman is landing right
     */
    private Animation<TextureRegion> landingRightAnimation;

    
    /**
     * The animation used when the stickman is jumping left
     */
    private Animation<TextureRegion> jumpingLeftAnimation;
    

    /**
     * The animation used when the stickman is jumping right
     */
    private Animation<TextureRegion> jumpingRightAnimation;
    
    
    /**
     * Time since the space stickman started the game. Used
     * to calculate the frame to show in animations.
     */
    private float stateTime = 0;

    /**
     * Stickman state (IDLE, WALKING_RIGHT, WALKING_LEFT, LIFT_OFF, JUMPING, LANDING)
     */
    private StickmanModel.Stickman_Animation stickmanState;

    

    /**
     * Stickman facing direction (LEFT, RIGHT)
     */
    private StickmanModel.Stickman_Facing_Direction stickmanFacingDirection;
    
    
    /**
     * Constructs a stickman model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     */
    public StickmanView(LpoortalGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this stickman.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the sprite representing this stickman
     */
    @Override
    public Sprite createSprite(LpoortalGame game) {
        idleLeftAnimation = createIdleLeftAnimation(game);
        idleRightAnimation = createIdleRightAnimation(game);
        
        walkingLeftAnimation = createWalkingLeftAnimation(game);
        walkingRightAnimation = createWalkingRightAnimation(game);
        
        liftOffLeftAnimation = createLiftOffLeftAnimation(game);
        liftOffRightAnimation = createLiftOffRightAnimation(game);
        
        jumpingLeftAnimation = createJumpingLeftAnimation(game);
        jumpingRightAnimation = createJumpingRightAnimation(game);
        
        landingLeftAnimation = createLandingLeftAnimation(game);
        landingRightAnimation = createLandingRightAnimation(game);
        
        return new Sprite(idleRightAnimation.getKeyFrame(stateTime));
    }

    /**
     * Creates the animation used when the stickman is idle (left)
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is idle (right)
     */
    private Animation<TextureRegion> createIdleLeftAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.IDLE, 
        									Stickman_Facing_Direction.LEFT);
    }
    
    /**
     * Creates the animation used when the stickman is idle (right)
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is idle (right)
     */
    private Animation<TextureRegion> createIdleRightAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.IDLE, 
        									Stickman_Facing_Direction.RIGHT);
    }
    
    /**
     * Creates the animation used when the stickman is walking left
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is walking left
     */
    private Animation<TextureRegion> createWalkingLeftAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.WALKING, 
        									Stickman_Facing_Direction.LEFT);
    }
    
    /**
     * Creates the animation used when the stickman is walking right
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is walking right
     */
    private Animation<TextureRegion> createWalkingRightAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.WALKING, 
        									Stickman_Facing_Direction.RIGHT);
    }
    
    /**
     * Creates the animation used when the stickman is lifting off left
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is lifting off left
     */
    private Animation<TextureRegion> createLiftOffLeftAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.LIFT_OFF, 
        									Stickman_Facing_Direction.LEFT);
    }
    
    /**
     * Creates the animation used when the stickman is lifting off right
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is lifting off right
     */
    private Animation<TextureRegion> createLiftOffRightAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.LIFT_OFF, 
        									Stickman_Facing_Direction.RIGHT);
    }
    
    /**
     * Creates the animation used when the stickman is jumping left
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is jumping left
     */
    private Animation<TextureRegion> createJumpingLeftAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.JUMPING, 
        									Stickman_Facing_Direction.LEFT);
    }
    
    /**
     * Creates the animation used when the stickman is jumping right
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is jumping right
     */
    private Animation<TextureRegion> createJumpingRightAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.JUMPING, 
        									Stickman_Facing_Direction.RIGHT);
    }
    
    /**
     * Creates the animation used when the stickman is landing left
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is landing left
     */
    private Animation<TextureRegion> createLandingLeftAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.LANDING, 
        									Stickman_Facing_Direction.LEFT);
    }
    
    /**
     * Creates the animation used when the stickman is landing right
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the animation used when the stickman is landing right
     */
    private Animation<TextureRegion> createLandingRightAnimation(LpoortalGame game) {
        return game.getTextureManager()
        							.getStickmanAnimation(
        									Stickman_Animation.LANDING, 
        									Stickman_Facing_Direction.RIGHT);
    }
    
    
    

    /**
     * Updates this ship model. Also save and resets
     * the accelerating flag from the model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);

        stickmanState = ((StickmanModel)model).getState();
        stickmanFacingDirection = ((StickmanModel)model).getFacingDirection();
        		
        ((StickmanModel)model).setState(Stickman_Animation.IDLE);
        								
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the accelerating flag.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        
        switch (stickmanState) {
		case WALKING:
			setWalkingSprite();
			break;
		case LIFT_OFF:
			setLiftOffSprite();
			break;
		case JUMPING:
			setJumpingSprite();
			break;
		case LANDING:
			setLandingSprite();
			break;
		default:
			setIdleSprite();
			break;
		}

        sprite.draw(batch);
    }

	private void setIdleSprite() {
		if(stickmanFacingDirection.equals(Stickman_Facing_Direction.LEFT)) {
			sprite.setRegion(idleLeftAnimation.getKeyFrame(stateTime, true));
		} else {
			sprite.setRegion(idleRightAnimation.getKeyFrame(stateTime, true));
		}
	}
	
	private void setWalkingSprite() {
		if(stickmanFacingDirection.equals(Stickman_Facing_Direction.LEFT)) {
			sprite.setRegion(walkingLeftAnimation.getKeyFrame(stateTime, true));
		} else {
			sprite.setRegion(walkingRightAnimation.getKeyFrame(stateTime, true));
		}
	}
	
	private void setLiftOffSprite() {
		if(stickmanFacingDirection.equals(Stickman_Facing_Direction.LEFT)) {
			sprite.setRegion(liftOffLeftAnimation.getKeyFrame(stateTime, true));
		} else {
			sprite.setRegion(liftOffRightAnimation.getKeyFrame(stateTime, true));
		}
	}
	
	private void setJumpingSprite() {
		if(stickmanFacingDirection.equals(Stickman_Facing_Direction.LEFT)) {
			sprite.setRegion(jumpingLeftAnimation.getKeyFrame(stateTime, true));
		} else {
			sprite.setRegion(jumpingRightAnimation.getKeyFrame(stateTime, true));
		}
	}
	
	private void setLandingSprite() {
		if(stickmanFacingDirection.equals(Stickman_Facing_Direction.LEFT)) {
			sprite.setRegion(landingLeftAnimation.getKeyFrame(stateTime, true));
		} else {
			sprite.setRegion(landingRightAnimation.getKeyFrame(stateTime, true));
		}
	}
}
