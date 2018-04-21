package view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoortal.game.LpoortalGame;

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

}
