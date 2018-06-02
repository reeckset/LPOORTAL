package view.entities;

import static view.entities.LevelScreen.PIXEL_TO_METER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import controller.entities.PortalBody;
import model.entities.EntityModel;
import model.entities.PortalModel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A view representing a portal
 */
public class PortalView extends EntityView {
    
    /**
     * Time since the space portal started the game. Used
     * to calculate the frame to show in animations.
     */
    private float stateTime = 0;
    
    /**
     * game object this portal view corresponds to
     **/
	private LpoortalGame game;
	
	private Color color;
	
	private int WIDTH = 150;
	
	private int HEIGHT = 40;
    
    
    /**
     * Constructs a portal view.
     *
     */
    public PortalView() {
    	super();
        this.game = LpoortalGame.getInstance();     
    }

    /**
     * Creates a sprite representing this portal.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the sprite representing this portal
     */
    @Override
    public Sprite createSprite() {
        
        Sprite sprite = new Sprite(game.getTextureManager().getPortalAnimation()
        					  .getKeyFrame(stateTime));
        sprite.setSize(PortalBody.WIDTH / LevelScreen.PIXEL_TO_METER,
        					  PortalBody.HEIGHT / LevelScreen.PIXEL_TO_METER);
        return sprite;
    }
    

    /**
     * Updates this portal model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);
        
        this.color = ((PortalModel)model).getColor();             								
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the portal state.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        sprite.setRegion(game.getTextureManager().getPortalAnimation().getKeyFrame(stateTime, true));
        sprite.setColor(color); 
        sprite.draw(batch);
    }

}
