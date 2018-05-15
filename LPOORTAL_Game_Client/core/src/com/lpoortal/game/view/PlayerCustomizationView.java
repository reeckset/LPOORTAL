package com.lpoortal.game.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoortal.game.LPOORTAL_Game;

public class PlayerCustomizationView extends ScreenView{

    public enum Player_Skin {COWBOY, SPACEMAN, NINJA, CHEF, LOL};
    private Player_Skin selectedSkin = Player_Skin.COWBOY;
    private Image tick;
    private final int TICK_Y = 450;
    private Image selectedTileOverlay;
    private static final int TILE_HEIGHT = SkinTile.TILE_HEIGHT;
    private static final int TILE_WIDTH = SkinTile.TILE_WIDTH;
    private static final int TILE_Y = 220;

    TextureManager.Player_Color selectedColor = TextureManager.Player_Color.BLUE;

    @Override
    public void render(float delta){
        super.render(delta);
        if(selectedColor == TextureManager.Player_Color.ORANGE){
            tick.setPosition(140, TICK_Y);
        }else{
            tick.setPosition(260, TICK_Y);
        }
    }

    public PlayerCustomizationView() {
        super();
        createUI();
    }

    private void createUI(){
        addPickColorLabel();
        addColorPickers();
        addNameLabel();
        addNameInput();
        addTiles();
        addSelectedOverlay();
        addTick();
        selectedTileOverlay.setPosition((VP_WIDTH - TILE_WIDTH*2)/2,TILE_Y);
    }

    private void addTick() {
        tick = new Image(textureManager.getTexture(TextureManager.Object_Texture.TICK));
        tick.setSize(50, 50);
        stage.addActor(tick);
    }

    private void addSelectedOverlay() {
        selectedTileOverlay = new Image(textureManager.getTexture(TextureManager.Object_Texture.TILE_SELECTED));
        selectedTileOverlay.setSize(TILE_WIDTH, TILE_HEIGHT);
        stage.addActor(selectedTileOverlay);
    }

    private void addNameLabel() {
        centerImage(TextureManager.Object_Texture.INSERT_NAME_LABEL, 100, 400);
    }

    private void addPickColorLabel(){
        centerImage(TextureManager.Object_Texture.PICK_COLOR_LABEL, 100, VP_HEIGHT - 75);
    }

    private void addColorPickers(){
        int width = VP_WIDTH/3;

        addOrangeButton(width);

        addBlueButton(width);
    }

    private void addOrangeButton(int width) {
        ButtonView orangeBtn = new ButtonView(new Point(VP_WIDTH/2 - width, VP_HEIGHT - width - 75), new Point(width,width),
                textureManager.getTexture(TextureManager.Object_Texture.COLOR_PICKER_ORANGE_UP),
                textureManager.getTexture(TextureManager.Object_Texture.COLOR_PICKER_ORANGE_DOWN));

        stage.addActor(orangeBtn);
        orangeBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedColor = TextureManager.Player_Color.ORANGE;
            }
        });
    }

    private void addBlueButton(int width) {
        ButtonView blueBtn = new ButtonView(new Point(VP_WIDTH/2, VP_HEIGHT - width - 75), new Point(width,width),
                textureManager.getTexture(TextureManager.Object_Texture.COLOR_PICKER_BLUE_UP),
                textureManager.getTexture(TextureManager.Object_Texture.COLOR_PICKER_BLUE_DOWN));

        stage.addActor(blueBtn);
        blueBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedColor = TextureManager.Player_Color.BLUE;
            }
        });
    }

    private void addNameInput(){
        TextField textField = new TextField("", textureManager.getTextInputStyle());
        textField.setMaxLength(10);
        stage.addActor(textField);
        textField.setPosition(0,330);
        textField.setSize(VP_WIDTH, 50);
        stage.setKeyboardFocus(textField);
        textField.getOnscreenKeyboard().show(true);
    }

    private void addTiles(){
        addCowboyTile();
        addSpacemanTile();
        addChefTile();
        addNinjaTile();
        addLOLTile();
        addReadyTile();
    }

    private void addReadyTile() {
        final int x =VP_WIDTH/2;
        final int y = TILE_Y-TILE_HEIGHT*2;
        ButtonView readyTile = new ButtonView(new Point(x, y), new Point(TILE_WIDTH, TILE_HEIGHT),
                textureManager.getTexture(TextureManager.Object_Texture.TILE_READY_UP),
                textureManager.getTexture(TextureManager.Object_Texture.TILE_DOWN
                ));
        stage.addActor(readyTile);
        readyTile.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x1, float y1) {
                //TODO SET READY
                LPOORTAL_Game.getInstance().changeState(LPOORTAL_Game.State.DRAWING_STATE);
            }
        });
    }

    private void addLOLTile() {
        stage.addActor(new SkinTile(
                (VP_WIDTH - TILE_WIDTH*2)/2, TILE_Y-TILE_HEIGHT*2,
                Player_Skin.LOL, TextureManager.Object_Texture.TILE_LOL_UP,
                this
        ));
    }

    private void addNinjaTile() {
        stage.addActor(new SkinTile(
                VP_WIDTH/2, TILE_Y-TILE_HEIGHT,
                Player_Skin.NINJA, TextureManager.Object_Texture.TILE_NINJA_UP,
                this
        ));
    }

    private void addChefTile() {
        stage.addActor(new SkinTile(
                (VP_WIDTH - TILE_WIDTH*2)/2, TILE_Y-TILE_HEIGHT,
                Player_Skin.CHEF, TextureManager.Object_Texture.TILE_CHEF_UP,
                this
        ));
    }

    private void addSpacemanTile() {
        stage.addActor(new SkinTile(
                VP_WIDTH/2, TILE_Y,
                Player_Skin.SPACEMAN, TextureManager.Object_Texture.TILE_SPACEMAN_UP,
                this
        ));
    }

    private void addCowboyTile() {
        stage.addActor(new SkinTile(
                (VP_WIDTH - TILE_WIDTH*2)/2, TILE_Y,
                Player_Skin.COWBOY, TextureManager.Object_Texture.TILE_COWBOY_UP,
                this
        ));
    }

    public void setSelectedSkin(Player_Skin skin){
        selectedSkin = skin;
    }


    public void setSelectedTileOverlayPosition(int x, int y) {
        selectedTileOverlay.setPosition(x,y);
    }

}
