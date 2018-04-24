package view.entities;

import static view.LevelScreen.PIXEL_TO_METER;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;

public class DrawnLineView extends EntityView {

	/**
     * Constructs a drawn line view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             texture manager to get textures.
     */
    public DrawnLineView(LpoortalGame game) {
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
        Texture texture = game.getTextureManager().getLineTexture();
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
    
    @Override
    public void update(EntityModel model) {
    	DrawnLineModel line = (DrawnLineModel) model;
        sprite.setCenter(line.getX() / PIXEL_TO_METER, line.getY() / PIXEL_TO_METER);
        sprite.setScale(line.getLength() / PIXEL_TO_METER / this.sprite.getWidth(), line.THICKNESS / PIXEL_TO_METER / this.sprite.getHeight());
        sprite.setRotation((float) (line.getAngle()*180/Math.PI));
    }

}
