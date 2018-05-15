package view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.lpoortal.game.LpoortalGame;

public abstract class GUIScreen extends ScreenAdapter {
    protected static final int VP_WIDTH = 640;
    protected static final int VP_HEIGHT = 360;
    protected static final int LANDSCAPE_MARGIN = 155;
    protected Stage stage = new Stage(new ExtendViewport(VP_WIDTH, VP_HEIGHT));
    protected TextureManager textureManager;
    protected Color backgroundColor = new Color( 1, 1, 1, 1 );
    protected LpoortalGame game;

    public GUIScreen(LpoortalGame game){
    	this.game = game;
        this.textureManager = game.getTextureManager();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta){
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    protected void clearScreen(){
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    protected void landscapeMode(){
            stage.getCamera().rotate(90, 0, 0, 1);
    }

    protected void centerImage(TextureManager.GUI_Texture guiTextureName, int widthPercent, int y){
        Texture texture = textureManager.getGUITexture(guiTextureName);
        Image img = new Image(new TextureRegion(texture));
        int width = widthPercent * VP_WIDTH / 100;
        int height = width*texture.getHeight()/texture.getWidth();
        img.setSize(width, height);
        img.setPosition((VP_WIDTH - width)/2, y);
        stage.addActor(img);
    }
    
    protected void showImage(TextureManager.GUI_Texture guiTextureName, int x, int y, int w, int h){
    	Texture texture = textureManager.getGUITexture(guiTextureName);
        Image img = new Image(new TextureRegion(texture));
        img.setSize(w, h);
        img.setPosition(x, y);
        stage.addActor(img);
    }


}
