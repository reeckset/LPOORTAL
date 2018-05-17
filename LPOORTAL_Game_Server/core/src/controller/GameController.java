package controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.PlayerClient;
import com.lpoortal.game.network.SocketCommunicator;

import controller.entities.CursorBody;
import controller.entities.DrawnLineBody;
import controller.entities.StickmanBody;
import model.GameModel;
import model.entities.CursorModel;
import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.StickmanModel;
import view.entities.CursorView;
import view.entities.LevelScreen;

/**
 * Controls the physics aspect of the game.
 */

public class GameController implements ContactListener {
    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * The arena width in meters.
     */
    public static final int LEVEL_WIDTH = 50;

	/**
     * The arena height in meters.
     */
    public static final int LEVEL_HEIGHT = 70;
    
    public static final float GRAVITY = 30;
    
    public static final int JUMP_STRENGTH = 20;

    public static final float STICKMAN_SPEED = 10f;
    
    boolean wasDrawing = false;
    
	float lastCursorPosX = 0;
	
	float lastCursorPosY = 0; 

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The stickman body.
     */
    private final StickmanBody stickmanBody;
    
    /**
     * The cursor body.
     */
    private final CursorBody cursorBody;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;

    /**
     * Lines that should be added in the next simulation step.
     */
    
    private boolean isPlayer1Drawer = true;
    
    SocketCommunicator player1;
	SocketCommunicator player2;
    
    private List<DrawnLineModel> linesToAdd = new ArrayList<DrawnLineModel>();


    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     *
     */
    private GameController() {
        world = new World(new Vector2(0, -GRAVITY), true);
        
        GameModel gameInstance = GameModel.getInstance();
        
        this.stickmanBody = new StickmanBody(world, gameInstance.getStickman());
        this.cursorBody = new CursorBody(world, gameInstance.getCursor());

        List<DrawnLineModel> drawnLines = GameModel.getInstance().getDrawnLines();
        this.drawLine(10, 10, 40, 10);
        
        world.setContactListener(this);
    }

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     *
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {
        GameModel.getInstance().update(delta);

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }
        
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            verifyBounds(body);
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }
        
        stickmanBody.update();
        applyClientInput();
    }

	/**
     * Takes the last received messages by the Network Manager
     * 
     */
    private void applyClientInput() {
    	//TODO MOVE THESE TWO LINES TO THE CONSTRUCTOR IF POSSIBLE
    	player1 = NetworkManager.getInstance().getPlayer1();
        player2 = NetworkManager.getInstance().getPlayer2();
    	
    	ClientToServerMsg drawerPlayer, stickmanPlayer;
    	if(isPlayer1Drawer) {
    		drawerPlayer = player1.getLastMessage();
    		stickmanPlayer = player2.getLastMessage();
    	}else {
    		drawerPlayer = player2.getLastMessage();
    		stickmanPlayer = player1.getLastMessage();
    	}
    	
    	cursorBody.updatePosition(drawerPlayer.dx, drawerPlayer.dy);
    	handleDraw(drawerPlayer.actionBtn);
    	
    	movePlayer(stickmanPlayer.dx, stickmanPlayer.actionBtn);
    	
	}

  
    private void movePlayer(float dx, boolean actionBtn) {
       
        if (actionBtn) {
            GameController.getInstance().jump();
        }
        if(dx < 0) {
        	GameController.getInstance().moveLeft(-dx);
        }else if(dx > 0) {
            GameController.getInstance().moveRight(dx);
        }
       
	}

	/**
     * Handles the drawing
     */
	private void handleDraw(boolean willDraw) {
		
		float currX = cursorBody.getX() + CursorView.CURSOR_SIZE * LevelScreen.PIXEL_TO_METER /2; //Offset will put the position  
		float currY = cursorBody.getY() - CursorView.CURSOR_SIZE * LevelScreen.PIXEL_TO_METER /2; //in the corner of the cursor (pencil tip)
		
		if(wasDrawing && willDraw) {
			if(Math.sqrt(Math.pow(currX - lastCursorPosX, 2) + Math.pow(currY - lastCursorPosY, 2)) > 0.5f) {
			drawLine(lastCursorPosX, lastCursorPosY, currX, currY);
			} else {
				return;
			}
		}
		wasDrawing = willDraw;
		lastCursorPosX = currX;
		lastCursorPosY = currY;
	}

	/**
     * Verifies if the body is inside the arena bounds and if not
     * keeps it in bounds.
     *
     * @param body The body to be verified.
     */
    private void verifyBounds(Body body) {
        if (body.getPosition().x < 0)
            body.setTransform(0, body.getPosition().y, body.getAngle());

        if (body.getPosition().y < 0)
            body.setTransform(body.getPosition().x, 0, body.getAngle());

        if (body.getPosition().x > LEVEL_WIDTH)
            body.setTransform(LEVEL_WIDTH, body.getPosition().y, body.getAngle());

        if (body.getPosition().y > LEVEL_HEIGHT)
            body.setTransform(body.getPosition().x, LEVEL_HEIGHT, body.getAngle());
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }


    /**
     * Makes the stickman jump. The acceleration takes into consideration the
     * constant acceleration force and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void jump() {
    	if(!( ((StickmanModel)stickmanBody.getUserData()).isJumping())) {
    		stickmanBody.setLinearVelocity(0, JUMP_STRENGTH);
            ((StickmanModel)stickmanBody.getUserData()).setJumping(true);
    	}
    }
    

	public void moveLeft(float delta) {
		stickmanBody.setLinearVelocity(-STICKMAN_SPEED * delta, stickmanBody.getSpeedY());
	}
	public void moveRight(float delta) {
		stickmanBody.setLinearVelocity(STICKMAN_SPEED * delta, stickmanBody.getSpeedY());
	}

    /**
     * Draws a Line on Level
     */
    public void drawLine(float xi, float yi, float xf, float yf) {
        DrawnLineModel line = new DrawnLineModel(xi, yi, xf, yf);
        GameModel.getInstance().addLine(line);
        DrawnLineBody body = new DrawnLineBody(world, line);
        body.setLinearVelocity(0, 0);
    }

    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {
    	Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        
        if ((bodyA.getUserData() instanceof StickmanModel && contact.getFixtureA().isSensor() && bodyB.getUserData() instanceof DrawnLineModel)
          || (bodyB.getUserData() instanceof StickmanModel && contact.getFixtureB().isSensor() && bodyA.getUserData() instanceof DrawnLineModel)) {
        	stickmanBody.increaseNbrCollidedObjs();
        	((StickmanModel)stickmanBody.getUserData()).setJumping(false);
        }
        
        
    }

	@Override
    public void endContact(Contact contact) {
		Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        
        if ((bodyA.getUserData() instanceof StickmanModel && contact.getFixtureA().isSensor() && bodyB.getUserData() instanceof DrawnLineModel)
          || (bodyB.getUserData() instanceof StickmanModel && contact.getFixtureB().isSensor() && bodyA.getUserData() instanceof DrawnLineModel)) {
        	stickmanBody.decreaseNbrCollidedObjs();
        }
        
        if(!stickmanBody.isColliding()) {
        	((StickmanModel)stickmanBody.getUserData()).setJumping(true);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void removeFlagged() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            if (((EntityModel)body.getUserData()).isFlaggedToBeRemoved()) {
                GameModel.getInstance().remove((EntityModel) body.getUserData());
                world.destroyBody(body);
            }
        }
    }
    
    public static int getWidth() {
		return LEVEL_WIDTH;
	}

	public static int getHeight() {
		return LEVEL_HEIGHT;
	}
	
	public static float limitBoundsX(float x) {
		if (x < 0) { 
			return 0;
		}
        else if (x > LEVEL_WIDTH) {
        	return LEVEL_WIDTH;
        }
		return x;
	}
	
	public static float limitBoundsY(float y) {
		if (y < 0) { 
			return 0;
		}
        else if (y > LEVEL_HEIGHT) {
        	return LEVEL_HEIGHT;
        }
		return y;
	}
}
