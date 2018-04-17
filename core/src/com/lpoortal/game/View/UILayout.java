package com.lpoortal.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class UILayout {
    Stage stage = new Stage(new ExtendViewport(640, 360));
    ButtonView jumpBtn;
    TextureManager textureManager;
    public UILayout(TextureManager textureManager){
        this.textureManager = textureManager;
        createControls();


        Gdx.input.setInputProcessor(stage);
    }

    public void draw(float delta){
        stage.act(delta);
        stage.draw();
    }

    private void createControls(){
        jumpBtn = new ButtonView(new Point(420, 20),
                                new Point(200, 200),
                                textureManager.getTexture(TextureManager.ObjectTexture.JUMP_BTN_UP),
                                textureManager.getTexture(TextureManager.ObjectTexture.JUMP_BTN_DOWN));
        stage.addActor(jumpBtn);

        Image logo = new Image(new TextureRegion(textureManager.getTexture(TextureManager.ObjectTexture.LOGO)));

        logo.setSize(256, 144);
        logo.setPosition(192, 200);
        stage.addActor(logo);

        stage.addActor(new JoyStick(new Point(20, 20),
                new Point(200,200), textureManager));
    }

}
