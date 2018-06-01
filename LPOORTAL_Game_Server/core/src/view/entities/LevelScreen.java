package view.entities;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.lpoortal.game.LpoortalGame;

import controller.GameController;
import model.GameModel;
import model.entities.CursorModel;
import model.entities.DrawnLineModel;
import model.entities.InkJarModel;
import model.entities.PortalModel;
import model.entities.StickmanModel;


public class LevelScreen extends ScreenAdapter {
    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = false;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_WIDTH = 50;
    
    /**
     * The game this screen belongs to.
     */
    private final LpoortalGame game;
    
    /**
     * The camera used to show the viewport.
     */
    private final OrthographicCamera camera;

    /**
     * A renderer used to debug the physical fixtures.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugCamera;

    /**
     * A manager for texture assets, responsible for loading the sprites/animations
     */
	private TextureManager textureManager;
	
    private static final int VP_WIDTH = 640;
    
    private static final int VP_HEIGHT = 360;
	
    private Stage stage = new Stage(new ExtendViewport(VP_WIDTH, VP_HEIGHT));
    
    private Sprite inkAmount;

    /**
     * Creates a Level screen.
     */
    public LevelScreen() {
        this.game = LpoortalGame.getInstance();

        loadAssets();

        camera = createCamera();
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(
        		getWidthPixels(), 
        		getWidthPixels() * getViewportRatio());

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }
        
        return camera;
    }

    private float getWidthPixels() {
    	return VIEWPORT_WIDTH / PIXEL_TO_METER;
	}

	private float getViewportRatio() {
    	return ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
	}

	/**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.textureManager = this.game.getTextureManager();
        
        inkAmount = new Sprite(textureManager.getInkBar());
        inkAmount.setSize(4 / PIXEL_TO_METER , GameController.getHeight() / PIXEL_TO_METER);
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
         
        GameController.getInstance().removeFlagged();

        GameController.getInstance().update(delta);
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 255/255f, 255/255f, 255/255f, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
   
    }

    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        List<DrawnLineModel> lines = GameModel.getInstance().getDrawnLines();
        for (DrawnLineModel line : lines) {
            EntityView view = new DrawnLineView();
            view.update(line);
            view.draw(game.getBatch());
        }
        
        List<InkJarModel> inkJars = GameModel.getInstance().getInkJars();
        for (InkJarModel model : inkJars) {
            EntityView view = ViewFactory.makeView(model);
            view.update(model);
            view.draw(game.getBatch());
        }


        CursorModel cursorModel = GameModel.getInstance().getCursor();
        EntityView view = ViewFactory.makeView(cursorModel);
        

        view.update(cursorModel);
        view.draw(game.getBatch());
        
        PortalModel portalModel = GameModel.getInstance().getPortal();
        view = ViewFactory.makeView(portalModel);
        
        view.update(portalModel);
        view.draw(game.getBatch());
        
        StickmanModel stickmanModel = GameModel.getInstance().getStickman();
        view = ViewFactory.makeView(stickmanModel);
        
        view.update(stickmanModel);
        view.draw(game.getBatch());
        
        inkAmount.setColor(GameModel.getInstance().getDrawerColor());
        inkAmount.setScale(1f, GameModel.getInstance().getInkAmount()/15f);
        inkAmount.setRegion(0, 0, 1f, GameModel.getInstance().getInkAmount()/15f);
        inkAmount.setCenterY(0f);
        inkAmount.setPosition(0, - inkAmount.getHeight()/2 - 150);
        inkAmount.draw(game.getBatch());

    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = this.textureManager.getBackground();
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        
        Sprite s = new Sprite(background);
        s.setPosition(0,0);
        s.setSize(getWidthPixels(), getWidthPixels() / getViewportRatio());
        s.draw(game.getBatch());
    }
    
    protected void clearScreen(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }
}
