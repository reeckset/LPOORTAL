package com.lpoortal.game.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.MessageManager;

public class ControlsView extends ScreenView{

    private ButtonView jumpBtn;
    private JoyStick joystick;

    public ControlsView(){

        super();
        textureManager.refreshTextures();
        landscapeMode();
        createControls();
    }

    private void createControls(){
        jumpBtn = new ButtonView(new Point(440 - LANDSCAPE_MARGIN, LANDSCAPE_MARGIN),
                                new Point(200, 200),
                                textureManager.getTexture(TextureManager.Object_Texture.JUMP_BTN_UP),
                                textureManager.getTexture(TextureManager.Object_Texture.JUMP_BTN_DOWN));
        stage.addActor(jumpBtn);

        Image logo = new Image(new TextureRegion(textureManager.getTexture(TextureManager.Object_Texture.LOGO)));

        logo.setSize(256, 144);
        logo.setPosition(192 - LANDSCAPE_MARGIN, 200 + LANDSCAPE_MARGIN);
        stage.addActor(logo);
        joystick = new JoyStick(new Point(20 - LANDSCAPE_MARGIN, LANDSCAPE_MARGIN),
                new Point(200,200), 10, textureManager);
        stage.addActor(joystick);
    }

    @Override
    public void render(float delta){
        super.render(delta);
        MessageManager.getInstance().getClient().setNextSendingMessage(
                new ClientToServerMsg(LPOORTAL_Game.getInstance().getState().toString(),
                        joystick.getKnobPercentX(), joystick.getKnobPercentY(),
                        jumpBtn.isPressed())
        );
    }

}
