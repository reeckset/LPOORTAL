package com.lpoortal.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.lpoortal.game.Controller.HandleGyro;
import com.lpoortal.game.LPOORTAL_Game;

public class ControlsView extends ScreenView{

    private ButtonView jumpBtn;

    public ControlsView(LPOORTAL_Game g, TextureManager textureManager){

        super(g, textureManager);

        createControls();
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

    @Override
    public void render(float delta){
        super.render(delta);
        jumpBtn.setSize(50,50);
        jumpBtn.setX((float)HandleGyro.calc()[0]*6 + 300);
        jumpBtn.setY((float)HandleGyro.calc()[1]*6 + 100);
    }

}
