package view.entities;

import static view.entities.LevelScreen.PIXEL_TO_METER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import controller.entities.StickmanBody;
import model.entities.EntityModel;
import model.entities.StickmanModel;
import model.entities.StickmanModel.Stickman_Animation;
import view.entities.StickmanVisualDetails.Stickman_Skin;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A view representing a stickman
 */
public class StickmanView extends EntityView {
    
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
     * Stickman x position
     */
    private float x;
    
    /**
     * Stickman y position
     */
    private float y;

    /**
     * game object this stickman view corresponds to
     **/
	private LpoortalGame game;
	
	private Color color;
	
	private Sprite skinSprite;
	private Stickman_Skin skin;
    
    
    /**
     * Constructs a stickman model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     */
    public StickmanView(LpoortalGame game) {
    	super(game);
        this.game = game;
        stickmanState = stickmanState.IDLE;
        stickmanFacingDirection = stickmanFacingDirection.RIGHT;
        
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
        
        Sprite sprite = new Sprite(game.getTextureManager()
        					  .getStickmanAnimation(Stickman_Animation.IDLE, stickmanFacingDirection.RIGHT)
        					  .getKeyFrame(stateTime));
        sprite.setSize(StickmanBody.WIDTH / LevelScreen.PIXEL_TO_METER,
        					  StickmanBody.HEIGHT / LevelScreen.PIXEL_TO_METER);
        return sprite;
    }
    

    /**
     * Updates this stickman model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);

        stickmanState = ((StickmanModel)model).getState();
        stickmanFacingDirection = ((StickmanModel)model).getFacingDirection();
        x = ((StickmanModel)model).getX();
        y = ((StickmanModel)model).getY();
        
        this.color = ((StickmanModel)model).getColor();
        
        if(this.skinSprite == null) {
        	this.skinSprite = new Sprite(game.getTextureManager().getStickmanSkinAnimation(
        			stickmanState, stickmanFacingDirection, ((StickmanModel)model).getSkin())
        			.getKeyFrame(stateTime)) ;
        }
        
        this.skin = ((StickmanModel)model).getSkin();
        
        								
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the stickman state.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        sprite.setRegion(game.getTextureManager().getStickmanAnimation(
						stickmanState, 
						stickmanFacingDirection).getKeyFrame(stateTime, true));
        
        if(skinSprite != null)
        skinSprite.setRegion(game.getTextureManager().getStickmanSkinAnimation(stickmanState, stickmanFacingDirection, skin).getKeyFrame(stateTime, true));

        sprite.setColor(color); 
        sprite.draw(batch);
        
        if(skinSprite != null) {
        skinSprite.setCenter(x / PIXEL_TO_METER, y / PIXEL_TO_METER);
        skinSprite.draw(batch);
        }
    }

}
