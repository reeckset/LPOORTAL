package com.lpoortal.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class ButtonView extends Button {

    /**
     *
     * @param position button position
     * @param size button size
     * @param texSourceUp normal texture
     * @param texSourceDown button pressed texture
     */
    public ButtonView(Point position, Point size, Texture texSourceUp, Texture texSourceDown){
        super(new SpriteDrawable(new Sprite(texSourceUp)),
                new SpriteDrawable(new Sprite(texSourceDown)));
        this.setBounds(position.getX(), position.getY(), size.getX(), size.getY());
    }

}
