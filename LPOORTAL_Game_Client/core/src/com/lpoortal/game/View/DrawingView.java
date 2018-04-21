package com.lpoortal.game.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lpoortal.game.Controller.GyroCalculator;
import com.lpoortal.game.Controller.GyroManager;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.Network.ClientToServerMsg;

public class DrawingView extends ScreenView{

    private double cursorX = 300;
    private double cursorY = 100;

    private GyroManager gyro;

    public DrawingView(TextureManager textureManager){

        super(textureManager);

        gyro = new GyroManager();
        new Thread(gyro).start();

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

        LPOORTAL_Game.getInstance().getClient().sendMessage(
                new ClientToServerMsg(LPOORTAL_Game.getInstance().getState().toString(), (float) gyro.getX(), (float) gyro.getY(), false)
        );
    }

}
