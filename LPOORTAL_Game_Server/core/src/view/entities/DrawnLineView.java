package view.entities;

import static view.entities.LevelScreen.PIXEL_TO_METER;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

import model.entities.DrawnLineModel;
import model.entities.EntityModel;

public class DrawnLineView extends EntityView {

	/**
     * Constructs a drawn line view.
     *
     */
    public DrawnLineView() {
        super();
    }

    /**
     * Creates a sprite representing this cursor.
     *
     * @return the sprite representing this cursor
     */
    public Sprite createSprite() {
        Texture texture = LpoortalGame.getInstance().getTextureManager().getLinePreview();
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
    
    @Override
    public void update(EntityModel model) {
    	DrawnLineModel line = (DrawnLineModel) model;
    	if(!line.isPreview()) {
    		this.sprite = new Sprite(LpoortalGame.getInstance().getTextureManager().getLineTexture());
    	}
        sprite.setCenter(line.getX() / PIXEL_TO_METER, line.getY() / PIXEL_TO_METER);
        sprite.setScale(line.getLength() / PIXEL_TO_METER / this.sprite.getWidth(), DrawnLineModel.THICKNESS / PIXEL_TO_METER / this.sprite.getHeight());
        sprite.setRotation((float) (line.getAngle()*180/Math.PI));
    }

}
