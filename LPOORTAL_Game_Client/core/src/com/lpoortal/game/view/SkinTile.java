package com.lpoortal.game.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoortal.game.LPOORTAL_Game;

public class SkinTile extends ButtonView{

    public static final int TILE_WIDTH = 170;
    public static final int TILE_HEIGHT = 110;

    private final int x;
    private final int y;

    PlayerCustomizationView screen;
    PlayerCustomizationView.Player_Skin skin;

    public SkinTile(final int x, final int y, PlayerCustomizationView.Player_Skin skin, TextureManager.Object_Texture texture, PlayerCustomizationView screen){
        super(new Point(x, y), new Point(TILE_WIDTH, TILE_HEIGHT),
                LPOORTAL_Game.getInstance().getTextureManager().getTexture(texture),
                LPOORTAL_Game.getInstance().getTextureManager().getTexture(TextureManager.Object_Texture.TILE_DOWN));

        this.x = x;
        this.y = y;
        this.screen = screen;
        this.skin = skin;

        setListener();
    }

    private void setListener() {
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x1, float y1) {
                screen.setSelectedSkin(skin);
                screen.setSelectedTileOverlayPosition(x, y);
            }
        });
    }
}
