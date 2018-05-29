package com.lpoortal.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.controller.GyroManager;
import com.lpoortal.game.network.ClientToServerMsg;
import com.lpoortal.game.network.MessageManager;

public class GameOverView extends ScreenView{
    private ButtonView backBtn;
    private Boolean pressedBackBtn = false;

    public GameOverView(){
        super();
        createUI();
    }

    private void createUI() {
        centerImage(TextureManager.Object_Texture.LOGO, 80, VP_HEIGHT - 170);
        backBtn = new ButtonView(new Point(80, 170),
                new Point(200, 200),
                textureManager.getTexture(TextureManager.Object_Texture.BACK_BTN_UP),
                textureManager.getTexture(TextureManager.Object_Texture.BACK_BTN_DOWN));
        stage.addActor(backBtn);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pressedBackBtn = true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        MessageManager.getInstance().getClient().setNextSendingMessage(
                new ClientToServerMsg(LPOORTAL_Game.State.GAME_OVER_STATE.toString(),
                        0,0,pressedBackBtn)
        );
    }
}
