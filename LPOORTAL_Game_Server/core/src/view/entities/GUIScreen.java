package view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    /**
     * Creates a GUI Screen
     */
    public GUIScreen(){
    	this.game = LpoortalGame.getInstance();
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

    /**
     * Clears the screen with the default background color
     */
    protected void clearScreen(){
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    /**
     * Rotates the camera so that it looks like landscape mode
     */
    protected void landscapeMode(){
            stage.getCamera().rotate(90, 0, 0, 1);
    }

    /**
     * Adds the specified texture to the GUI, centering it horizontally
     * 
     * @param guiTextureName the GUI_Texture descriptor
     * @param widthPercent - width of the texture in relation to the screen width
     * @param y - y position
     */
    protected void centerImage(TextureManager.GUI_Texture guiTextureName, int widthPercent, int y){
        Texture texture = textureManager.getGUITexture(guiTextureName);
        Image img = new Image(new TextureRegion(texture));
        int width = widthPercent * VP_WIDTH / 100;
        int height = width*texture.getHeight()/texture.getWidth();
        img.setSize(width, height);
        img.setPosition((VP_WIDTH - width)/2, y);
        stage.addActor(img);
    }
    
    protected Image showImage(Texture texture, int x, int y, int w, int h){
    /**
     * Places the given texture in the given position
     * @param guiTextureName - the GUI_Texture descriptor
     * @param y - y position
     * @param x - x position
     * @param h - texture height
     * @param w - texture width
     * @return the added image
     */
        Image img = new Image(new TextureRegion(texture));
        img.setSize(w, h);
        img.setPosition(x, y);
        stage.addActor(img);
        return img;
    }
    
    /**
     * Shows a Label with Sub-text formatting
     * @param text - the text of the label
     * @param x - x position
     * @param y - y position
     * @param w - label width
     * @param h - label height
     * @return the label
     */
    protected Label showSubText(String text, int x, int y, int w, int h) {
    	Label label = new Label(text, textureManager.getSubTextStyle());
        stage.addActor(label);
        label.setPosition(x, y);
        label.setSize(w, h);
        return label;
    }
    
    /**
     * Shows a Label with normal text formatting
     * @param text - the text of the label
     * @param x - x position
     * @param y - y position
     * @param w - label width
     * @param h - label height
     * @return the label
     */
    protected Label showLabel(String text, int x, int y, int w, int h) {
    	Label label = new Label(text, textureManager.getLabelStyle());
        stage.addActor(label);
        label.setPosition(x, y);
        label.setSize(w, h);
        return label;
    }

}
