package com.lpoortal.game.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class JoyStick extends Touchpad {
    Point position, size;

    /**
     *
     * @param position joystick position
     * @param size joystick size
     * @param deadZoneVal deadzone delta
     * @param textureManager game's texture manager
     */
    public JoyStick(Point position, Point size, int deadZoneVal, TextureManager textureManager){
        super(deadZoneVal, getTouchpadStyle(textureManager));
        this.position = position;
        this.size = size;
        this.setBounds(position.getX(), position.getY(), size.getX(), size.getX());
    }

    private static TouchpadStyle getTouchpadStyle(TextureManager textureManager){
        Skin skin = new Skin();
        skin.add("background", textureManager.getTexture(TextureManager.Object_Texture.JOYSTICK_BG));
        skin.add("knob", textureManager.getTexture(TextureManager.Object_Texture.JOYSTICK_KNOB));

        TouchpadStyle ts = new TouchpadStyle();
        ts.background = skin.getDrawable("background");
        ts.knob = skin.getDrawable("knob");

        return ts;
    }
}
