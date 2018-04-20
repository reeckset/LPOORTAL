package com.lpoortal.game.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lpoortal.game.Controller.HandleGyro;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.Network.ClientToServerMsg;

public class ControlsView extends ScreenView{

    private ButtonView jumpBtn;

    private double buttonX = 300;
    private double buttonY = 100;

    public ControlsView(TextureManager textureManager){

        super(textureManager);

        createControls();
    }

    private void createControls(){
        jumpBtn = new ButtonView(new Point(420, 20),
                                new Point(200, 200),
                                textureManager.getTexture(TextureManager.Object_Texture.JUMP_BTN_UP),
                                textureManager.getTexture(TextureManager.Object_Texture.JUMP_BTN_DOWN));
        stage.addActor(jumpBtn);

        Image logo = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.LOGO)));

        logo.setSize(256, 144);
        logo.setPosition(192, 200);
        stage.addActor(logo);

        stage.addActor(new JoyStick(new Point(20, 20),
                new Point(200,200), textureManager));
    }

    @Override
    public void render(float delta){
        super.render(delta);
        jumpBtn.setSize(50,50);
        double[] gyro =  HandleGyro.calc();
        if(buttonX + gyro[0] > -25 && buttonX + gyro[0] < 640 - 25)
        buttonX += gyro[0];
        if(buttonY + gyro[1] > -25 && buttonY + gyro[1] < 360 - 25)
        buttonY += gyro[1];

        jumpBtn.setX((float)buttonX);
        jumpBtn.setY((float)buttonY);

        LPOORTAL_Game.getInstance().getClient().sendMessage(
                new ClientToServerMsg(LPOORTAL_Game.getInstance().getState().toString(), (float)buttonX, (float)buttonY, false)
        );
    }

}
