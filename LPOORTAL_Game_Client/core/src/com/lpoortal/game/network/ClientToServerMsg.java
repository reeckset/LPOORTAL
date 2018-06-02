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
     * Creates a Message
     * @param dx horizontal delta
     * @param dy vertical delta
     * @param actionBtn button pressed
     * @param controllerState state of this controller
     * @param playerColor color of the player
     * @param playerSkin skin of the player
     * @param playerName name of the player
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
     * Creates a Message
     */
    public ClientToServerMsg() {
        this(0,0,false,"","","","");
    }

    /**
     * Creates a Message
     * @param dx horizontal delta
     * @param dy vertical delta
     * @param actionBtn button pressed
     * @param controllerState state of this controller
     */
    public ClientToServerMsg(String controllerState, float dx, float dy, boolean actionBtn){
        this(dx, dy, actionBtn, controllerState, "", "", "");
    }

    /**
     * Creates a Message
     * @param actionBtn button pressed
     * @param controllerState state of this controller
     * @param playerColor color of the player
     * @param playerSkin skin of the player
     * @param playerName name of the player
     */
    public ClientToServerMsg(String controllerState, String playerColor, String playerSkin, String playerName, boolean actionBtn){
        this(0,0,actionBtn,controllerState, playerColor, playerSkin, playerName);
    }
}
