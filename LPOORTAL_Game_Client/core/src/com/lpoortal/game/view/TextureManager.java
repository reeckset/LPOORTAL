package com.lpoortal.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.HashMap;

public class TextureManager {

    public enum Player_Color {ORANGE, BLUE};
    public enum Object_Texture {LOGO,
        JOYSTICK_KNOB, JOYSTICK_BG,
        JUMP_BTN_UP, JUMP_BTN_DOWN,
        MOVEMENT_TUTORIAL,
        KEYBOARD_KEY_DOWN, KEYBOARD_ERASE, KEYBOARD_DONE,
        CONNECTION_INSTRUCTIONS,
        SPLITTER,
        COLOR_PICKER_ORANGE_UP, COLOR_PICKER_ORANGE_DOWN, COLOR_PICKER_BLUE_UP, COLOR_PICKER_BLUE_DOWN, PICK_COLOR_LABEL,
        INSERT_NAME_LABEL,
        TILE_COWBOY_UP, TILE_SPACEMAN_UP, TILE_CHEF_UP, TILE_NINJA_UP, TILE_LOL_UP, TILE_DOWN, TILE_READY_UP, TILE_SELECTED,
        TICK
        };

    private HashMap<Object_Texture, Texture> textures;
    private AssetManager assetManager;

    private TextField.TextFieldStyle textInputStyle;

    public TextureManager(Player_Color color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException("Null player color given to Texture Manager");
        }

        createTextInputStyle();

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
        textures.put(Object_Texture.COLOR_PICKER_ORANGE_UP, getTextureFromSource("color_picker_orange.png"));
        textures.put(Object_Texture.COLOR_PICKER_ORANGE_DOWN, getTextureFromSource("color_picker_orange_down.png"));
        textures.put(Object_Texture.COLOR_PICKER_BLUE_UP, getTextureFromSource("color_picker_blue.png"));
        textures.put(Object_Texture.COLOR_PICKER_BLUE_DOWN, getTextureFromSource("color_picker_blue_down.png"));
        textures.put(Object_Texture.PICK_COLOR_LABEL, getTextureFromSource("pick_color_label.png"));
        textures.put(Object_Texture.INSERT_NAME_LABEL, getTextureFromSource("insert_name_label.png"));

        textures.put(Object_Texture.TILE_COWBOY_UP, getTextureFromSource("tile_cowboy.png"));
        textures.put(Object_Texture.TILE_SPACEMAN_UP, getTextureFromSource("tile_spaceman.png"));
        textures.put(Object_Texture.TILE_CHEF_UP, getTextureFromSource("tile_chef.png"));
        textures.put(Object_Texture.TILE_LOL_UP, getTextureFromSource("tile_lol.png"));
        textures.put(Object_Texture.TILE_NINJA_UP, getTextureFromSource("tile_ninja.png"));
        textures.put(Object_Texture.TILE_READY_UP, getTextureFromSource("tile_ready.png"));
        textures.put(Object_Texture.TILE_DOWN, getTextureFromSource("tile_pressed.png"));
        textures.put(Object_Texture.TILE_SELECTED, getTextureFromSource("selected_tile.png"));
        textures.put(Object_Texture.TICK, getTextureFromSource("tick.png"));



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

    public Texture getKeyboardKeyTexture(int i) {
        return new Texture("keyboard_" + Integer.toHexString(i) + ".png");
    }

    private void createTextInputStyle(){
        textInputStyle = new TextField.TextFieldStyle(
                new BitmapFont(Gdx.files.internal("purisa.fnt")),
                Color.BLACK,
                new SpriteDrawable(new Sprite(new Texture("text_input_cursor.png"))),
                new SpriteDrawable(new Sprite(new Texture("text_input_selection.png"))),
                new SpriteDrawable(new Sprite(new Texture("text_input_background.png"))));
        textInputStyle.font.getData().setScale(0.7f);
    }

    public TextField.TextFieldStyle getTextInputStyle(){
        return textInputStyle;
    }



}
