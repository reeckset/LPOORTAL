package view.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.LpoortalGame;

import controller.entities.CursorBody;
import model.entities.CursorModel;
import model.entities.EntityModel;
import model.entities.StickmanModel;

public class CursorView extends EntityView {

	public static final int CURSOR_SIZE = 80;
	
	private Color color;
	
	/**
     * Constructs a cursor view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     */
    public CursorView(LpoortalGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this cursor.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     * @return the sprite representing this cursor
     */
    public Sprite createSprite(LpoortalGame game) {
        Texture texture = game.getTextureManager().getCursor();

        return new Sprite(texture);
    }
    
    /**
     * Updates this cursor model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);
        
        this.color = ((CursorModel)model).getColor();
        								
    }
    
    @Override
    public void draw(SpriteBatch batch) {
    	sprite.setSize(CURSOR_SIZE, CURSOR_SIZE);
    	sprite.setColor(color);
    	sprite.draw(batch);
    }

}
