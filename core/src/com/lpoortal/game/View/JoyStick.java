package com.lpoortal.game.View;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class JoyStick extends Touchpad {
    Point position, size;

    public JoyStick(Point position, Point size, TextureManager textureManager){
        super((size.getX() + size.getY()) / 40, getTouchpadStyle(textureManager));
        this.position = position;
        this.size = size;
        this.setBounds(position.getX(), position.getY(), size.getX(), size.getX());
    }

    private static TouchpadStyle getTouchpadStyle(TextureManager textureManager){
        Skin skin = new Skin();
        skin.add("background", textureManager.getTexture(TextureManager.ObjectTexture.JOYSTICK_BG));
        skin.add("knob", textureManager.getTexture(TextureManager.ObjectTexture.JOYSTICK_KNOB));

        TouchpadStyle ts = new TouchpadStyle();
        ts.background = skin.getDrawable("background");
        ts.knob = skin.getDrawable("knob");

        return ts;
    }
}
