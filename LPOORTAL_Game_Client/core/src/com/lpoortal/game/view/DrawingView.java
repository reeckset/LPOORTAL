package com.lpoortal.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lpoortal.game.controller.GyroManager;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.MessageManager;

import javax.swing.plaf.TextUI;

public class DrawingView extends ScreenView{

    private GyroManager gyro;

    public DrawingView(){

        super();

        gyro = new GyroManager();
        new Thread(gyro).start();

        createUI();
    }

    private void createUI(){
        centerImage(TextureManager.Object_Texture.LOGO, 80, VP_HEIGHT - 170);

        centerImage(TextureManager.Object_Texture.MOVEMENT_TUTORIAL, 40,170);
    }

    @Override
    public void render(float delta){
        super.render(delta);

        MessageManager.getInstance().getClient().setNextSendingMessage(
                new ClientToServerMsg(LPOORTAL_Game.getInstance().getState().toString(), (float) gyro.getX(), (float) gyro.getY(), Gdx.input.isTouched())
        );
    }

}
