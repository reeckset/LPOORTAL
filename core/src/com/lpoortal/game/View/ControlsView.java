package com.lpoortal.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.lpoortal.game.LPOORTAL_Game;

public class ControlsView extends ScreenAdapter {
    private final LPOORTAL_Game game;
    private final OrthographicCamera camera;
    private final static float VIEWPORT_WIDTH = 10;

    private UILayout uiLayout;
    private TextureManager textureManager;

    private Color backgroundColor = new Color( 1, 1, 1, 1 );

    public ControlsView(LPOORTAL_Game g, TextureManager textureManager){
        this.game = g;
        this.camera = createOrthoCamera();
        this.uiLayout = new UILayout(textureManager);
        this.textureManager = textureManager;
    }

    private OrthographicCamera createOrthoCamera(){
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, getViewportHeight());

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        return camera;
    }

    @Override
    public void render(float delta){
        clearScreen();

        uiLayout.draw(delta);
    }

    private float getScreenRatio(){
        return ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
    }

    private float getViewportHeight(){
        return VIEWPORT_WIDTH * getScreenRatio();
    }

    private void clearScreen(){
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }
}
