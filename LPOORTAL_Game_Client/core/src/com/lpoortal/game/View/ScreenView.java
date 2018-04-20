package com.lpoortal.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.lpoortal.game.Controller.HandleGyro;
import com.lpoortal.game.LPOORTAL_Game;

public abstract class ScreenView extends ScreenAdapter {
    protected Stage stage = new Stage(new ExtendViewport(640, 360));
    protected TextureManager textureManager;
    protected Color backgroundColor = new Color( 1, 1, 1, 1 );

    public ScreenView(TextureManager textureManager){
        this.textureManager = textureManager;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta){
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    protected void clearScreen(){
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    protected void portraitMode(){
            stage.getCamera().rotate(270, 0, 0, 1);
    }


}
