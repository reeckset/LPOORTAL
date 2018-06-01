package view.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoortal.game.LpoortalGame;

import model.entities.CursorModel;
import model.entities.EntityModel;

public class CursorView extends EntityView {

	public static final int CURSOR_SIZE = 80;
	
	private Color color;
	
	/**
     * Constructs a cursor view.
     *
     */
    public CursorView() {
        super();
    }

    /**
     * Creates a sprite representing this cursor.
     *
     * @return the sprite representing this cursor
     */
    public Sprite createSprite() {
        Texture texture = LpoortalGame.getInstance().getTextureManager().getCursor();

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
