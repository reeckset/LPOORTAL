package view.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import controller.GameController;
import controller.entities.InkJarBody;
import model.entities.EntityModel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A view representing a portal
 */
public class InkJarView extends EntityView {
	
	private Color color;
    
    
    /**
     * Constructs an ink jar model.
     *
     */
    public InkJarView() {
    	super();
    }

    /**
     * Creates a sprite representing this ink jar.
     *
     * @return the sprite representing this portal
     */
    @Override
    public Sprite createSprite() {
        
        Sprite sprite = new Sprite(LpoortalGame.getInstance().getTextureManager().getInkJar());
        sprite.setSize(InkJarBody.WIDTH / LevelScreen.PIXEL_TO_METER,
        		InkJarBody.HEIGHT / LevelScreen.PIXEL_TO_METER);
        return sprite;
    }
    

    /**
     * Updates this ink jar view.
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
