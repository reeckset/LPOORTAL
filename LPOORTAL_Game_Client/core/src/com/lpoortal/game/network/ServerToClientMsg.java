package com.lpoortal.game.network;

import java.io.Serializable;

public class ServerToClientMsg implements Serializable {
    public final String controllerState;

    /**
     *
     * @param controllerState the state to set the controller to
     */
    public ServerToClientMsg(String controllerState){
        this.controllerState = controllerState;
    }
}
