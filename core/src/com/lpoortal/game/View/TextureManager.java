package com.lpoortal.game.View;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureManager {

    public enum PlayerColor {ORANGE, BLUE};
    public enum ObjectTexture {LOGO, JOYSTICK_KNOB, JOYSTICK_BG, JUMP_BTN_UP, JUMP_BTN_DOWN};

    private HashMap<ObjectTexture, Texture> textures;

    private PlayerColor playerColor;

    public TextureManager(PlayerColor color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException("Null player color given to Texture Manager");
        }
        this.playerColor = color;

        textures = new HashMap<ObjectTexture, Texture>();

        populateGeneralTextures();
        if(color == PlayerColor.BLUE) {
            populateBlueColoredTextures();
        }else{
            populateOrangeColoredTextures();
        }
    }

    /**
     * Loads textures that are equal in both color versions
     */
    private void populateGeneralTextures(){
        textures.put(ObjectTexture.JOYSTICK_BG, getTextureFromSource("background_joystick.png"));
        textures.put(ObjectTexture.LOGO, getTextureFromSource("logo.png"));
    }

    private void populateBlueColoredTextures(){
        textures.put(ObjectTexture.JOYSTICK_KNOB, getTextureFromSource("knob_joystick_blue.png"));
        textures.put(ObjectTexture.JUMP_BTN_DOWN, getTextureFromSource("jump_btn_blue_down.png"));
        textures.put(ObjectTexture.JUMP_BTN_UP, getTextureFromSource("jump_btn_blue_up.png"));

    }

    private void populateOrangeColoredTextures(){
        textures.put(ObjectTexture.JOYSTICK_KNOB, getTextureFromSource("knob_joystick_orange.png"));
        textures.put(ObjectTexture.JUMP_BTN_DOWN, getTextureFromSource("jump_btn_orange_down.png"));
        textures.put(ObjectTexture.JUMP_BTN_UP, getTextureFromSource("jump_btn_orange_up.png"));
    }

    //TODO Use asset manager
    private Texture getTextureFromSource(String source){
        return new Texture(source);
    }

    public Texture getTexture(ObjectTexture obj){
        return textures.get(obj);
    }



}
