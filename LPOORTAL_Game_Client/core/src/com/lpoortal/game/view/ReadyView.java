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

}
