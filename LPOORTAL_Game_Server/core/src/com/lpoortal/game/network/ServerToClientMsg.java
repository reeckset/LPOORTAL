package com.lpoortal.game.network;

import java.io.Serializable;

public class ServerToClientMsg  implements Serializable{
    public final String controllerState;

    ServerToClientMsg(String controllerState){
        this.controllerState = controllerState;
    }
}
