package com.lpoortal.game.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ControlsView extends ScreenView{

    private ButtonView jumpBtn;

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
    }

}
