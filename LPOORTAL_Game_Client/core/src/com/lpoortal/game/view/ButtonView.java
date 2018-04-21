package com.lpoortal.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class ButtonView extends Button {

    public ButtonView(Point position, Point size, Texture texSourceUp, Texture texSourceDown){
        super(new SpriteDrawable(new Sprite(texSourceUp)),
                new SpriteDrawable(new Sprite(texSourceDown)));
        this.setBounds(position.getX(), position.getY(), size.getX(), size.getY());
    }


}
