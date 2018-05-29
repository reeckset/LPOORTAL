package com.lpoortal.game.view;

import com.badlogic.gdx.Gdx;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.controller.GyroManager;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.MessageManager;

public class ReadyView extends ScreenView{

    public ReadyView(){
        super();
        createUI();
    }

    private void createUI(){
        centerImage(TextureManager.Object_Texture.READY_BACKGROUND, 100, 0);
    }

    @Override
    public void render(float delta){
        super.render(delta);
        MessageManager.getInstance().getClient().setNextSendingMessage(new ClientToServerMsg(
                LPOORTAL_Game.State.READY_STATE.toString(), LPOORTAL_Game.getInstance().getPlayerColor(), LPOORTAL_Game.getInstance().getPlayerSkin(), LPOORTAL_Game.getInstance().getPlayerName(), false
        ));
    }

}
