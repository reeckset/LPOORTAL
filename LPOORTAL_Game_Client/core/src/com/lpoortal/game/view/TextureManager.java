package com.lpoortal.game.view;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureManager {

    public enum Player_Color {ORANGE, BLUE};
    public enum Object_Texture {LOGO,
        JOYSTICK_KNOB, JOYSTICK_BG,
        JUMP_BTN_UP, JUMP_BTN_DOWN,
        MOVEMENT_TUTORIAL,
        KEYBOARD_KEY_DOWN, KEYBOARD_ERASE, KEYBOARD_DONE,
        CONNECTION_INSTRUCTIONS,
        SPLITTER};

    private HashMap<Object_Texture, Texture> textures;
    private AssetManager assetManager;

    public TextureManager(Player_Color color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException("Null player color given to Texture Manager");
        }

        assetManager = new AssetManager();
        textures = new HashMap<Object_Texture, Texture>();

        populateGeneralTextures();
        if(color == Player_Color.BLUE) {
            populateBlueColoredTextures();
        }else{
            populateOrangeColoredTextures();
        }
    }

    /**
     * Loads textures that are equal in both color versions
     */
    private void populateGeneralTextures(){
        textures.put(Object_Texture.JOYSTICK_BG, getTextureFromSource("background_joystick.png"));
        textures.put(Object_Texture.LOGO, getTextureFromSource("logo.png"));
        textures.put(Object_Texture.MOVEMENT_TUTORIAL, getTextureFromSource("movement_tutorial.png"));
        textures.put(Object_Texture.KEYBOARD_KEY_DOWN, getTextureFromSource("keyboard_down.png"));
        textures.put(Object_Texture.KEYBOARD_DONE, getTextureFromSource("keyboard_done.png"));
        textures.put(Object_Texture.KEYBOARD_ERASE, getTextureFromSource("keyboard_erase.png"));
        textures.put(Object_Texture.CONNECTION_INSTRUCTIONS, getTextureFromSource("connection_instructions.png"));
        textures.put(Object_Texture.SPLITTER, getTextureFromSource("splitter.png"));

    }

    private void populateBlueColoredTextures(){
        textures.put(Object_Texture.JOYSTICK_KNOB, getTextureFromSource("knob_joystick_blue.png"));
        textures.put(Object_Texture.JUMP_BTN_DOWN, getTextureFromSource("jump_btn_blue_down.png"));
        textures.put(Object_Texture.JUMP_BTN_UP, getTextureFromSource("jump_btn_blue_up.png"));

    }

    private void populateOrangeColoredTextures(){
        textures.put(Object_Texture.JOYSTICK_KNOB, getTextureFromSource("knob_joystick_orange.png"));
        textures.put(Object_Texture.JUMP_BTN_DOWN, getTextureFromSource("jump_btn_orange_down.png"));
        textures.put(Object_Texture.JUMP_BTN_UP, getTextureFromSource("jump_btn_orange_up.png"));
    }

    private Texture getTextureFromSource(String source){
        assetManager.load(source, Texture.class);
        assetManager.finishLoading();
        return assetManager.get(source);
    }

    public Texture getTexture(Object_Texture obj){
        return textures.get(obj);
    }

    public Texture getKeyboardKeyTexture(int i){
        return new Texture("keyboard_" + Integer.toHexString(i) + ".png");
    }



}
