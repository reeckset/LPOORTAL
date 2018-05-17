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

    public ClientToServerMsg(float dx, float dy, boolean actionBtn, String controllerState, String playerColor, String playerSkin, String playerName) {
        this.dx = dx;
        this.dy = dy;
        this.actionBtn = actionBtn;
        this.controllerState = controllerState;
        this.playerColor = playerColor;
        this.playerSkin = playerSkin;
        this.playerName = playerName;
    }
    
    public ClientToServerMsg() {
    	this(0,0,false,"","","","");
    }

    public ClientToServerMsg(String controllerState, float dx, float dy, boolean actionBtn){
        this(dx, dy, actionBtn, controllerState, "", "", "");
    }

    public ClientToServerMsg(String controllerState, String playerColor, String playerSkin, String playerName, boolean actionBtn){
        this(0,0,actionBtn,controllerState, playerColor, playerSkin, playerName);
    }
}

