package com.lpoortal.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.lpoortal.game.LPOORTAL_Game;

public class MovementView extends ScreenView{

    private ButtonView jumpBtn;

    public MovementView(LPOORTAL_Game g, TextureManager textureManager){

        super(g, textureManager);

        createUI();
    }

    private void createUI(){
        portraitMode();

        Image logo = new Image(new TextureRegion(textureManager.getTexture(TextureManager.ObjectTexture.LOGO)));
        logo.setSize(256, 144);
        logo.setPosition(192, 300);
        stage.addActor(logo);

        Image tutorial = new Image(new TextureRegion(textureManager.getTexture(TextureManager.ObjectTexture.MOVEMENT_TUTORIAL)));
        tutorial.setSize(144, 256);
        tutorial.setPosition(248, 0);
        stage.addActor(tutorial);
    }

}
