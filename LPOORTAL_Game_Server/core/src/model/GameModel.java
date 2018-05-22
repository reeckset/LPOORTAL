package model;

import java.util.ArrayList;
import java.util.List;

import controller.GameController;
import model.entities.CursorModel;
import model.entities.DrawnLineModel;
import model.entities.EntityModel;
import model.entities.PortalModel;
import model.entities.StickmanModel;
import view.entities.LevelScreen;

public class GameModel {
	/**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    
    /**
     *  The cursor controlled by the user in this game.
     */
    private CursorModel cursor;

    
    /**
     * The player controlled by one of the players.
     */
    private StickmanModel stickman;
    
    
    /**
     * User drawn lines on the screen.
     */
    private List<DrawnLineModel> drawnLines;
    
    /**
     * Portal
     */
    private PortalModel portal;
    
    private final int CURSOR_DEFAULT_X = 25;
    private final int CURSOR_DEFAULT_Y = 15;
    private final int STICKMAN_DEFAULT_X = 25;
    private final int STICKMAN_DEFAULT_Y = 15;
    private final int PORTAL_DEFAULT_X = 25;
    private final int PORTAL_DEFAULT_Y = 27;
    

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }
    
    /**
     * Constructs a game with a stickman in the specified starting position
     * and a cursor in the middle of the screen
     */
    private GameModel() {
    	this.cursor = new CursorModel(CURSOR_DEFAULT_X, CURSOR_DEFAULT_Y);
        this.stickman = new StickmanModel(STICKMAN_DEFAULT_X, STICKMAN_DEFAULT_Y);
        this.drawnLines = new ArrayList<DrawnLineModel>();
        this.portal = new PortalModel(PORTAL_DEFAULT_X, PORTAL_DEFAULT_Y);
    }
    
    public void resetGame() {
    	this.cursor.setPosition(CURSOR_DEFAULT_X, CURSOR_DEFAULT_Y);
        this.stickman.setPosition(STICKMAN_DEFAULT_X, STICKMAN_DEFAULT_Y);
        this.drawnLines = new ArrayList<DrawnLineModel>();
        this.portal.setPosition(PORTAL_DEFAULT_X, PORTAL_DEFAULT_Y);
    }

	public CursorModel getCursor() {
		return cursor;
	}

	public StickmanModel getStickman() {
		return stickman;
	}

	public List<DrawnLineModel> getDrawnLines() {
		return drawnLines;
	}
	
	public PortalModel getPortal() {
		return portal;
	}
    
	/**
     * Removes a model from this game.
     *
     * @param model the model to be removed
     */
    public void remove(EntityModel model) {
        if (model instanceof DrawnLineModel) {
            drawnLines.remove(model);
        }
    }

    /**
     * Adds a new line to the model
     *
     * @param lineModel the line model to be added
     */
    public void addLine(DrawnLineModel lineModel) {
        drawnLines.add(lineModel);
    }

    public void update(float delta) {
        
    }
    
}
