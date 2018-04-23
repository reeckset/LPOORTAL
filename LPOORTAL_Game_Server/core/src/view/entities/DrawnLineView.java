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
        return new Sprite(texture);
    }
    
    @Override
    public void update(EntityModel model) {
    	DrawnLineModel line = (DrawnLineModel) model;
        sprite.setCenter((line.getX()+line.getXf())/ 2 / PIXEL_TO_METER, (line.getY()+line.getYf())/ 2 / PIXEL_TO_METER);
        float xLength = line.getX()-line.getXf();
        float yLength = line.getY()-line.getYf();
        float length = (float) Math.sqrt(xLength*xLength+yLength*yLength);
        float angle = (float) Math.acos(xLength/length);
        sprite.setScale(length/45, 0.01f);
        if(yLength < 0) angle = -angle;
        sprite.setRotation((float) (angle*180/Math.PI));
    }

}
