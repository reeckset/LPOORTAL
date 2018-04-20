package com.lpoortal.game.Model.Network;

public class ServerToClientMsg {
    public final String controllerState;

    ServerToClientMsg(String controllerState){
        this.controllerState = controllerState;
    }
}
