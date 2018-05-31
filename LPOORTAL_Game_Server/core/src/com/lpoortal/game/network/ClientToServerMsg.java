package com.lpoortal.game.network;

import java.io.Serializable;

public class ClientToServerMsg implements Serializable {
    public final float dx;
    public final float dy;
    public final boolean actionBtn;
    public final String controllerState;
    public final String playerColor;
    public final String playerSkin;
    public final String playerName;

    /**
     * Creates a message with the given params
     * @param dx - horizontal delta
     * @param dy - vertical delta
     * @param actionBtn - boolean (is clicking button)
     * @param controllerState - state of the controller
     * @param playerColor - color of the player
     * @param playerSkin - skin of the player
     * @param playerName - the player name
     */
    public ClientToServerMsg(float dx, float dy, boolean actionBtn, String controllerState, String playerColor, String playerSkin, String playerName) {
        this.dx = dx;
        this.dy = dy;
        this.actionBtn = actionBtn;
        this.controllerState = controllerState;
        this.playerColor = playerColor;
        this.playerSkin = playerSkin;
        this.playerName = playerName;
    }
    
    /**
     * Creates a default message
     */
    public ClientToServerMsg() {
    	this(0,0,false,"","","","");
    }

    /**
     * Creates a message with the given params and the missing ones default
     * @param dx - horizontal delta
     * @param dy - vertical delta
     * @param actionBtn - boolean (is clicking button)
     * @param controllerState - state of the controller
     */
    public ClientToServerMsg(String controllerState, float dx, float dy, boolean actionBtn){
        this(dx, dy, actionBtn, controllerState, "", "", "");
    }

    /**
     * Creates a message with the given params, and the deltas = 0
     * @param actionBtn - boolean (is clicking button)
     * @param controllerState - state of the controller
     * @param playerColor - color of the player
     * @param playerSkin - skin of the player
     * @param playerName - the player name
     */
    public ClientToServerMsg(String controllerState, String playerColor, String playerSkin, String playerName, boolean actionBtn){
        this(0,0,actionBtn,controllerState, playerColor, playerSkin, playerName);
    }
}

