package com.lpoortal.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.controller.KeyboardController;
import com.lpoortal.game.network.Client;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.MessageManager;

import java.util.ArrayList;

public class ConnectView extends ScreenView{

    private static final int BUTTON_SIZE = 70;
    private static final int MARGIN_KEYBOARD = 140;

    KeyboardController kbController;
    ArrayList<Image> displayImgs = new ArrayList<Image>();

    public ConnectView(TextureManager textureManager) {
        super(textureManager);

        kbController = new KeyboardController();
        portraitMode();
        createUI();
    }

    @Override
    public void render(float delta){
        super.render(delta);
        updateDisplay();
    }

    private void createUI(){
        addKeyboardValues();
        addKeyboardDoneKey();
        addKeyboardEraseKey();
        createDisplay();
        addLogo();
        addInstructions();
        addSplitters();
    }

    private void setKeyEvent(ButtonView btn, int i){
        final int keyNbr = i;
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kbController.addKey(keyNbr);
            }
        });
    }

    private void addKeyboardValues(){
        for(int i = 0; i < 16; i++) {
            ButtonView newBtn = new ButtonView(new Point(i%4*BUTTON_SIZE + MARGIN_KEYBOARD, (3-i/4)*BUTTON_SIZE - MARGIN_KEYBOARD),
                    new Point(BUTTON_SIZE, BUTTON_SIZE),
                    textureManager.getKeyboardKeyTexture(i),
                    textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_KEY_DOWN));

            setKeyEvent(newBtn, i);
            stage.addActor(newBtn);
        }
    }

    private void addKeyboardDoneKey(){
        ButtonView doneBtn = new ButtonView(new Point(4*BUTTON_SIZE + MARGIN_KEYBOARD, - MARGIN_KEYBOARD),
                new Point(BUTTON_SIZE, BUTTON_SIZE),
                textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_DONE),
                textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_KEY_DOWN));
        doneBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kbController.submit();
            }
        });
        stage.addActor(doneBtn);
    }

    private void addKeyboardEraseKey(){
        ButtonView eraseBtn = new ButtonView(new Point(4*BUTTON_SIZE + MARGIN_KEYBOARD, 3*BUTTON_SIZE - MARGIN_KEYBOARD),
                new Point(BUTTON_SIZE, BUTTON_SIZE),
                textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_ERASE),
                textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_KEY_DOWN));
        eraseBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kbController.erase();
            }
        });
        stage.addActor(eraseBtn);
    }

    private void createDisplay(){
        for(int i = 0; i < 8; i++){
            Image newImg = new Image(textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_KEY_DOWN));
            newImg.setSize(45,  45);
            newImg.setPosition(i*45 + MARGIN_KEYBOARD, 160);
            displayImgs.add(newImg);
            stage.addActor(newImg);
        }
    }

    private void updateDisplay(){
        for(int i = 0; i < displayImgs.size(); i++){
            if(kbController.toString().length() > i) {
                displayImgs.get(i).setDrawable(new SpriteDrawable( new Sprite(textureManager.getKeyboardKeyTexture(kbController.getValAt(i)))));
            }else{
                displayImgs.get(i).setDrawable(new SpriteDrawable( new Sprite(textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_KEY_DOWN))));
            }
        }
    }

    private void addLogo(){
        Image logo = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.LOGO)));
        logo.setSize(256, 144);
        logo.setPosition(192, 360);
        stage.addActor(logo);
    }

    private void addInstructions(){
        Image instructions = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.CONNECTION_INSTRUCTIONS)));
        instructions.setSize(330, 150);
        instructions.setPosition(MARGIN_KEYBOARD + 10, 200);
        stage.addActor(instructions);
    }

    private void addSplitters(){
        Image splitter1 = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.SPLITTER)));
        splitter1.setSize(360, 32);
        splitter1.setPosition(MARGIN_KEYBOARD, 140);
        stage.addActor(splitter1);

        Image splitter2 = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.SPLITTER)));
        splitter2.setSize(360, 32);
        splitter2.setPosition(MARGIN_KEYBOARD, 190);
        stage.addActor(splitter2);
    }

}
