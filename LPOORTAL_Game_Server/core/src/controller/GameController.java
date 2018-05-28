package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lpoortal.game.LpoortalGame;
import com.lpoortal.game.LpoortalGame.CONTROLLER_STATE;
import com.lpoortal.game.LpoortalGame.STATE;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.NetworkManager;
import com.lpoortal.game.network.PlayerClient;
import com.lpoortal.game.network.SocketCommunicator;

import controller.entities.CursorBody;
import controller.entities.DrawnLineBody;
import controller.entities.InkJarBody;
import controller.entities.PortalBody;
import controller.entities.StickmanBody;
import model.GameModel;
import model.entities.CursorModel;
import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.InkJarModel;
import model.entities.PortalModel;
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
    private StickmanBody stickmanBody;
    
    /**
     * The portal body.
     */
    private PortalBody portalBody;
    
    /**
     * The cursor body.
     */
    private CursorBody cursorBody;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;

    /**
     * Lines that should be added in the next simulation step.
     */
    
    private boolean isPlayer1Drawer = true;
    
    
    //Line draw system
    private float inkAmount = 6;
    private float inkSpentOnLine = 0;
    private static final float MIN_INK_DRAWN = 3.1f;
    private static final float INK_PER_JAR = 5;
    private static final int INK_JAR_MIN_X = 3;
    private static final int INK_JAR_MAX_X = 45;
    private static final int INK_JAR_MIN_Y = 3;
    private static final int INK_JAR_MAX_Y = 25;
 
    private List<DrawnLineBody> linesDrawn = new ArrayList<DrawnLineBody>();
    private List<DrawnLineBody> currentPreviewLines = new ArrayList<DrawnLineBody>();
    private List<InkJarBody> inkJars = new ArrayList<InkJarBody>();
    
    SocketCommunicator player1;
	SocketCommunicator player2;
	
	private int score = 0;
    
    private boolean removeLines = false;
    
    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     *
     */
    private GameController() {

        world = new World(new Vector2(0, -GRAVITY), true);
        
        GameModel gameInstance = GameModel.getInstance();
    	
    	this.stickmanBody = new StickmanBody(world, gameInstance.getStickman());
        this.cursorBody = new CursorBody(world, gameInstance.getCursor());

        drawStartLine();
        createInkJars();
        
        player1 = NetworkManager.getInstance().getPlayer1();
        player2 = NetworkManager.getInstance().getPlayer2();
        
        portalBody = new PortalBody(world, gameInstance.getPortal());
        
        updatePlayerVisuals();
        
        world.setContactListener(this);
    }

    public int getScore() {
		return this.score;
	}
    
	private void drawStartLine() {
		this.drawLine(10, 10, 40, 10).setDefinitive();	
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
    	if(removeLines) {
        	resetLines();
        	cleanInkJars();
            createInkJars();
          	currentPreviewLines.clear();
        }
    	
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
        
        if(stickmanBody.getY() < 1) {
        	LpoortalGame.getInstance().setState(STATE.GAME_OVER);
        }

        applyClientInput();
    }

	private void cleanInkJars() {
		for(InkJarBody inkJar : inkJars) {
			EntityModel inkJarModel = ((EntityModel)inkJar.getUserData());
			if(inkJarModel != null) {
				inkJarModel.setFlaggedForRemoval(true);
			}
			
      	}
      	inkJars = new ArrayList<InkJarBody>();
	}

	private void resetLines() {
		for(DrawnLineBody line : linesDrawn) {
			((DrawnLineModel)line.getUserData()).setFlaggedForRemoval(true);
      	}
    	removeLines = false;
      	linesDrawn = new ArrayList<DrawnLineBody>();
      	drawStartLine();		
	}

	/**
     * Takes the last received messages by the Network Manager
     * 
     */
    private void applyClientInput() {
    	
    	ClientToServerMsg drawerPlayerMsg, stickmanPlayerMsg;
    	
    	if(isPlayer1Drawer) {
    		drawerPlayerMsg = player1.getLastMessage();
    		stickmanPlayerMsg = player2.getLastMessage();
    	}else {
    		drawerPlayerMsg = player2.getLastMessage();
    		stickmanPlayerMsg = player1.getLastMessage();
    	}
	    
    	if(drawerPlayerMsg != null && stickmanPlayerMsg != null) {	
	    	cursorBody.updatePosition(drawerPlayerMsg.dx, drawerPlayerMsg.dy);
	    	handleDraw(drawerPlayerMsg.actionBtn);
	    	
	    	movePlayer(stickmanPlayerMsg.dx, stickmanPlayerMsg.actionBtn);
    	}
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
				inkSpentOnLine += (float) Math.sqrt(Math.pow(lastCursorPosX-currX, 2) + Math.pow(lastCursorPosY-currY, 2));
				if(inkSpentOnLine < inkAmount) {
					currentPreviewLines.add(drawLine(lastCursorPosX, lastCursorPosY, currX, currY));
				}else {
					willDraw = false;
					if(inkSpentOnLine > MIN_INK_DRAWN) {
						makeLinesDefinitive();
						currentPreviewLines.clear();
						inkAmount -= inkSpentOnLine;
						inkSpentOnLine = 0;
					}
				}
			} else {
				return;
			}
		}
		if(wasDrawing && !willDraw) {
			if(inkSpentOnLine > MIN_INK_DRAWN && inkAmount >= inkSpentOnLine) {
				makeLinesDefinitive();
				inkAmount -= inkSpentOnLine;
			}else {
				destroyPreviewLines();
			}
			currentPreviewLines.clear();
			inkSpentOnLine = 0;
		}
		if(inkAmount < 0) { 
			inkAmount = 0; 
		}
		wasDrawing = willDraw;
		lastCursorPosX = currX;
		lastCursorPosY = currY;
	}

	private void destroyPreviewLines() {
		for(DrawnLineBody line : currentPreviewLines) {
			GameModel.getInstance().remove((DrawnLineModel)line.getUserData());
		}
	}

	private void makeLinesDefinitive() {
		for(DrawnLineBody line : currentPreviewLines) {
			line.setDefinitive();
	        linesDrawn.add(line);
		}
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
    public DrawnLineBody drawLine(float xi, float yi, float xf, float yf) {
        DrawnLineModel line = new DrawnLineModel(xi, yi, xf, yf);
        GameModel.getInstance().addLine(line);
        DrawnLineBody body = new DrawnLineBody(world, line);
        body.setLinearVelocity(0, 0);
        return body;
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
        
        if ((bodyA.getUserData() instanceof StickmanModel && contact.getFixtureA().getFriction() != 0 && bodyB.getUserData() instanceof DrawnLineModel)
          || (bodyB.getUserData() instanceof StickmanModel && contact.getFixtureB().getFriction() != 0 && bodyA.getUserData() instanceof DrawnLineModel)) {
        	stickmanBody.increaseNbrCollidedObjs();
        	((StickmanModel)stickmanBody.getUserData()).setJumping(false);
        }
        
        if ((bodyA.getUserData() instanceof StickmanModel && bodyB.getUserData() instanceof PortalModel)
                || (bodyB.getUserData() instanceof StickmanModel && bodyA.getUserData() instanceof PortalModel)) {
              	nextLevel();       	
        }
        
        if (bodyA.getUserData() instanceof StickmanModel && bodyB.getUserData() instanceof InkJarModel){
        	 this.inkAmount += INK_PER_JAR;
        	 ((EntityModel)bodyB.getUserData()).setFlaggedForRemoval(true);      	
        }
        if(bodyB.getUserData() instanceof StickmanModel && bodyA.getUserData() instanceof InkJarModel) {
          	 this.inkAmount += INK_PER_JAR;
          	((EntityModel)bodyA.getUserData()).setFlaggedForRemoval(true);
        }
        
    }

	private void nextLevel() {
		isPlayer1Drawer = !isPlayer1Drawer;
		LpoortalGame.getInstance().setState(STATE.COUNTDOWN);
		if(isPlayer1Drawer) {
      		NetworkManager.getInstance().getPlayer1().changeState(CONTROLLER_STATE.DRAWING_STATE);
      		NetworkManager.getInstance().getPlayer2().changeState(CONTROLLER_STATE.MOVEMENT_STATE);
      	}else {
      		NetworkManager.getInstance().getPlayer1().changeState(CONTROLLER_STATE.MOVEMENT_STATE);
      		NetworkManager.getInstance().getPlayer2().changeState(CONTROLLER_STATE.DRAWING_STATE);
      	}
      	stickmanBody.setLinearVelocity(0, 0);
      	GameModel.getInstance().resetGame();
      	updatePlayerVisuals();
      	NetworkManager.getInstance().getPlayer1().resetLastMessage();
      	NetworkManager.getInstance().getPlayer2().resetLastMessage();
      	removeLines = true;
      	this.inkAmount = 6;
      	this.inkSpentOnLine = 0;
      	this.score++;
	}

	private void createInkJars() {
		for(int i = 0; i < 2; i++) {
			InkJarModel model = new InkJarModel(getRandomInt(INK_JAR_MIN_X, INK_JAR_MAX_X), getRandomInt(INK_JAR_MIN_Y, INK_JAR_MAX_Y));
	        GameModel.getInstance().addInkJar(model);
	        InkJarBody body = new InkJarBody(world, model);
	        inkJars.add(body);
		}
	}

	@Override
    public void endContact(Contact contact) {
		Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        
        if ((bodyA.getUserData() instanceof StickmanModel && contact.getFixtureA().getFriction() != 0 && bodyB.getUserData() instanceof DrawnLineModel)
          || (bodyB.getUserData() instanceof StickmanModel && contact.getFixtureB().getFriction() != 0 && bodyA.getUserData() instanceof DrawnLineModel)) {
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
	
	private void updatePlayerVisuals(){
		if(isPlayer1Drawer) {
			((CursorModel)cursorBody.getUserData()).setColor(player1.getColor());
			((StickmanModel)stickmanBody.getUserData()).setColor(player2.getColor());
			((StickmanModel)stickmanBody.getUserData()).setSkin(player2.getSkin());
			((PortalModel)portalBody.getUserData()).setColor(player1.getColor());
		}else {
			((CursorModel)cursorBody.getUserData()).setColor(player2.getColor());
			((StickmanModel)stickmanBody.getUserData()).setColor(player1.getColor());
			((StickmanModel)stickmanBody.getUserData()).setSkin(player1.getSkin());
			((PortalModel)portalBody.getUserData()).setColor(player2.getColor());
		}
	}
	
	public Color getDrawerColor() {
		return isPlayer1Drawer ? player1.getColor() : player2.getColor();
	}
	
	private static int getRandomInt(int min, int max) {
		 return (int) (Math.floor((double)Math.random() * (max - min)) + min);
	}

	public void resetGame() {
		
		this.nextLevel();
		this.score = 0;
		
		this.updatePlayerVisuals();
		
	}

	
}
