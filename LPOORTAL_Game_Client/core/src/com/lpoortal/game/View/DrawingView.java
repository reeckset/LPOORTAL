package com.lpoortal.game.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lpoortal.game.Controller.HandleGyro;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.Network.ClientToServerMsg;

public class DrawingView extends ScreenView{

    private double cursorX = 300;
    private double cursorY = 100;

    public DrawingView(TextureManager textureManager){

        super(textureManager);

        createUI();
    }

    private void createUI(){
        portraitMode();

        Image logo = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.LOGO)));
        logo.setSize(256, 144);
        logo.setPosition(192, 300);
        stage.addActor(logo);

        Image tutorial = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.MOVEMENT_TUTORIAL)));
        tutorial.setSize(144, 256);
        tutorial.setPosition(248, 0);
        stage.addActor(tutorial);
    }

    @Override
    public void render(float delta){
        super.render(delta);
        double[] gyro =  HandleGyro.calc();
        if(cursorX + gyro[0] > -25 && cursorX + gyro[0] < 640 - 25)
            cursorX += gyro[0];
        if(cursorY + gyro[1] > -25 && cursorY + gyro[1] < 360 - 25)
            cursorY += gyro[1];


        LPOORTAL_Game.getInstance().getClient().sendMessage(
                new ClientToServerMsg(LPOORTAL_Game.getInstance().getState().toString(), (float) cursorX, (float) cursorY, false)
        );
    }

}
