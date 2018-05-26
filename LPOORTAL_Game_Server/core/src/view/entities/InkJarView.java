package view.entities;

import static view.entities.LevelScreen.PIXEL_TO_METER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import controller.GameController;
import controller.entities.InkJarBody;
import controller.entities.PortalBody;
import model.entities.EntityModel;
import model.entities.InkJarModel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A view representing a portal
 */
public class InkJarView extends EntityView {
    
    /**
     * game object this portal view corresponds to
     **/
	private LpoortalGame game;
	
	private Color color;
    
    
    /**
     * Constructs an ink jar model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     */
    public InkJarView(LpoortalGame game) {
    	super(game);
        this.game = game;     
    }

    /**
     * Creates a sprite representing this ink jar.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the sprite representing this portal
     */
    @Override
    public Sprite createSprite(LpoortalGame game) {
        
        Sprite sprite = new Sprite(game.getTextureManager().getInkJar());
        sprite.setSize(InkJarBody.WIDTH / LevelScreen.PIXEL_TO_METER,
        		InkJarBody.HEIGHT / LevelScreen.PIXEL_TO_METER);
        return sprite;
    }
    

    /**
     * Updates this ink jar model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);
        this.color = GameController.getInstance().getDrawerColor();             								
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
        sprite.setColor(color); 
        sprite.draw(batch);
    }

}
