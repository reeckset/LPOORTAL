package com.lpoortal.game.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.lpoortal.game.controller.IpKeyboardController;

import java.util.ArrayList;

public class ConnectView extends ScreenView{

    private static final int BUTTON_SIZE = VP_WIDTH/5;

    IpKeyboardController kbController;
    ArrayList<Image> displayImgs = new ArrayList<Image>();

    public ConnectView() {
        super();

        kbController = new IpKeyboardController();
        kbController.clear();
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
            ButtonView newBtn = new ButtonView(new Point(i%4*BUTTON_SIZE, (3-i/4)*BUTTON_SIZE),
                    new Point(BUTTON_SIZE, BUTTON_SIZE),
                    textureManager.getKeyboardKeyTexture(i),
                    textureManager.getTexture(TextureManager.Object_Texture.KEYBOARD_KEY_DOWN));

            setKeyEvent(newBtn, i);
            stage.addActor(newBtn);
        }
    }

    private void addKeyboardDoneKey(){
        ButtonView doneBtn = new ButtonView(new Point(4*BUTTON_SIZE, 0),
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
        ButtonView eraseBtn = new ButtonView(new Point(4*BUTTON_SIZE, 3*BUTTON_SIZE),
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
            newImg.setSize(VP_WIDTH/8,  VP_WIDTH/8);
            newImg.setPosition(i*VP_WIDTH/8, 300);
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
        centerImage(TextureManager.Object_Texture.LOGO, 70, VP_HEIGHT - 140);
    }

    private void addInstructions(){
        centerImage(TextureManager.Object_Texture.CONNECTION_INSTRUCTIONS, 80, VP_HEIGHT - 280);
    }

    private void addSplitters(){
        centerImage(TextureManager.Object_Texture.SPLITTER, 100, 325);
        centerImage(TextureManager.Object_Texture.SPLITTER, 100, 275);
    }

}
