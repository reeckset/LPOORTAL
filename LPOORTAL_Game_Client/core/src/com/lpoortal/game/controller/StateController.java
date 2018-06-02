package com.lpoortal.game.controller;

import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.network.MessageManager;
import com.lpoortal.game.network.ServerToClientMsg;

public class StateController implements Runnable {

    private long lastMessageMillis = 0;

    private static final int MESSAGE_RECEIVE_FREQUENCY_MILLIS = 100;

    private LPOORTAL_Game.State nextState = LPOORTAL_Game.State.CONNECT_STATE;

    private static StateController instance;

    private StateController(){
       instance = this;
    }

    @Override
    public void run() {
        while(true) {
            if (MessageManager.getInstance().getClient() != null &&
                    System.currentTimeMillis() - lastMessageMillis >= MESSAGE_RECEIVE_FREQUENCY_MILLIS) {
                ServerToClientMsg msg = MessageManager.getInstance().getClient().readMessage();
                if(msg != null) {
                    lastMessageMillis = System.currentTimeMillis();
                    this.nextState = LPOORTAL_Game.State.valueOf(msg.controllerState);
                    System.out.println(msg.controllerState);
                }

            }
        }
    }

    /**
     *
     * @return next state
     */
    public LPOORTAL_Game.State getNextState() {
        return nextState;
    }

    /**
     *
     * @return this instance
     */
    public static StateController getInstance(){
        if(instance == null){
            StateController.instance = new StateController();
        }
        return instance;
    }

    /**
     * Sets the next state
     * @param nextState state to be set on state update
     */
    public void setNextState(LPOORTAL_Game.State nextState) {
        this.nextState = nextState;
    }
}
