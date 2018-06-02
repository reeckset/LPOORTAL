package view.entities;

import static view.entities.LevelScreen.PIXEL_TO_METER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	
	private Color color;
	
	private Sprite skinSprite;
	private Stickman_Skin skin;
	private String name;
	
	private BitmapFont font;
    
    
    /**
     * Constructs a stickman view.
     *
     */
    public StickmanView() {
    	super();
        stickmanState = stickmanState.IDLE;
        stickmanFacingDirection = stickmanFacingDirection.RIGHT;
        font = new BitmapFont(Gdx.files.internal("purisa.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f);
    }

    /**
     * Creates a sprite representing this stickman.
     *
     * @return the sprite representing this stickman
     */
    @Override
    public Sprite createSprite() {
        
        Sprite sprite = new Sprite(LpoortalGame.getInstance().getTextureManager()
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
        	this.skinSprite = new Sprite(LpoortalGame.getInstance().getTextureManager().getStickmanSkinAnimation(
        			stickmanState, stickmanFacingDirection, ((StickmanModel)model).getSkin())
        			.getKeyFrame(stateTime)) ;
        }
        
        this.skin = ((StickmanModel)model).getSkin();
        this.name = ((StickmanModel)model).getName();
        
        								
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

        sprite.setRegion(LpoortalGame.getInstance().getTextureManager().getStickmanAnimation(
						stickmanState, 
						stickmanFacingDirection).getKeyFrame(stateTime, true));

        sprite.setColor(color); 
        sprite.draw(batch);
        
        if(skinSprite != null) {
	        skinSprite.setRegion(LpoortalGame.getInstance().getTextureManager().getStickmanSkinAnimation(stickmanState, stickmanFacingDirection, skin).getKeyFrame(stateTime, true));
	        skinSprite.setCenter(x / PIXEL_TO_METER, y / PIXEL_TO_METER);
	        skinSprite.draw(batch);
        }
        
        if(name != null) {
        	font.draw(batch, this.name, x / PIXEL_TO_METER, (y + StickmanBody.HEIGHT) / PIXEL_TO_METER, 1f, 1, false);
        }
    }

}
