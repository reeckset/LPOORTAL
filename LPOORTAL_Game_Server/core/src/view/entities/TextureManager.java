package view.entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import utilities.Pair;
import view.entities.StickmanVisualDetails.Stickman_Skin;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;

public class TextureManager {
	
	private HashMap<Pair<Stickman_Animation, Stickman_Facing_Direction>, Animation<TextureRegion>> stickmanAnimations;
	private HashMap<StickmanVisualDetails, Animation<TextureRegion>> stickmanSkinAnimations;
	
	private Texture background, cursor, lineTexture, linePreview, inkJar, backgroundCountdown, inkBar;
    private Label.LabelStyle labelStyle, subTextStyle;
    
    public enum GUI_Texture {LOGO, TICK, CROSS};
    public enum Player_Color {ORANGE, BLUE};
    private HashMap<GUI_Texture, Texture> guiTextures;
    
    private Animation<TextureRegion> portal;
	
	public TextureManager(){
		stickmanAnimations = new HashMap<Pair<Stickman_Animation,Stickman_Facing_Direction>, Animation<TextureRegion>>();
		stickmanSkinAnimations = new HashMap<StickmanVisualDetails, Animation<TextureRegion>>();
		guiTextures = new HashMap<GUI_Texture, Texture>();
		populateStaticTextures();
		populateGUITextures();
		populateAnimations();
		createLabelStyle();
		createSubTextStyle();
	}
	
	/**
	 * Populates the static textures (non-animated)
	 */
	private void populateStaticTextures() {
		this.background = new Texture("background.jpg");
		this.cursor = new Texture("pencil.png");
		this.lineTexture = new Texture("pencil_scratch.png");
		this.linePreview = new Texture("preview_line.png");
		this.inkJar = new Texture("ink_jar.png");
		this.backgroundCountdown = new Texture("between_levels.png");
		this.inkBar = new Texture("ink_bar.png");
	}

	private void populateAnimations() {
		//TODO THIS CAN BE REFACTORED SO THAT THE addPlayerAnimation FUNCTION ADDS THE ANIMATION FOR EACH SKIN
		
		addPlayerAnimation("Idle.png", Stickman_Animation.IDLE, 4f/30f);
		addPlayerAnimation("Lift_Off.png", Stickman_Animation.LIFT_OFF, 2f/30f);
		addPlayerAnimation("Walking.png", Stickman_Animation.WALKING, 3f/30f);
		addPlayerAnimation("mid_flight.png", Stickman_Animation.JUMPING, 30f/30f);
		addPlayerAnimation("Lift_Off.png", Stickman_Animation.LANDING, 3f/30f, true);
		
		addPlayerSkinAnimation("cowboy_idle.png", Stickman_Animation.IDLE, Stickman_Skin.COWBOY, 4f/30f, false);
		addPlayerSkinAnimation("cowboy_lift_off.png", Stickman_Animation.LIFT_OFF, Stickman_Skin.COWBOY, 2f/30f, false);
		addPlayerSkinAnimation("cowboy_walking.png", Stickman_Animation.WALKING, Stickman_Skin.COWBOY, 3f/30f, false);
		addPlayerSkinAnimation("cowboy_mid_flight.png", Stickman_Animation.JUMPING, Stickman_Skin.COWBOY, 30f/30f, false);
		addPlayerSkinAnimation("cowboy_lift_off.png", Stickman_Animation.LANDING, Stickman_Skin.COWBOY, 3f/30f, true);
		
		addPlayerSkinAnimation("spaceman_idle.png", Stickman_Animation.IDLE, Stickman_Skin.SPACEMAN, 4f/30f, false);
		addPlayerSkinAnimation("spaceman_lift_off.png", Stickman_Animation.LIFT_OFF, Stickman_Skin.SPACEMAN, 2f/30f, false);
		addPlayerSkinAnimation("spaceman_walking.png", Stickman_Animation.WALKING, Stickman_Skin.SPACEMAN, 3f/30f, false);
		addPlayerSkinAnimation("spaceman_mid_flight.png", Stickman_Animation.JUMPING, Stickman_Skin.SPACEMAN, 30f/30f, false);
		addPlayerSkinAnimation("spaceman_lift_off.png", Stickman_Animation.LANDING, Stickman_Skin.SPACEMAN, 3f/30f, true);
		
		addPlayerSkinAnimation("ninja_idle.png", Stickman_Animation.IDLE, Stickman_Skin.NINJA, 4f/30f, false);
		addPlayerSkinAnimation("ninja_lift_off.png", Stickman_Animation.LIFT_OFF, Stickman_Skin.NINJA, 2f/30f, false);
		addPlayerSkinAnimation("ninja_walking.png", Stickman_Animation.WALKING, Stickman_Skin.NINJA, 3f/30f, false);
		addPlayerSkinAnimation("ninja_mid_flight.png", Stickman_Animation.JUMPING, Stickman_Skin.NINJA, 30f/30f, false);
		addPlayerSkinAnimation("ninja_lift_off.png", Stickman_Animation.LANDING, Stickman_Skin.NINJA, 3f/30f, true);
		
		addPlayerSkinAnimation("chef_idle.png", Stickman_Animation.IDLE, Stickman_Skin.CHEF, 4f/30f, false);
		addPlayerSkinAnimation("chef_lift_off.png", Stickman_Animation.LIFT_OFF, Stickman_Skin.CHEF, 2f/30f, false);
		addPlayerSkinAnimation("chef_walking.png", Stickman_Animation.WALKING, Stickman_Skin.CHEF, 3f/30f, false);
		addPlayerSkinAnimation("chef_mid_flight.png", Stickman_Animation.JUMPING, Stickman_Skin.CHEF, 30f/30f, false);
		addPlayerSkinAnimation("chef_lift_off.png", Stickman_Animation.LANDING, Stickman_Skin.CHEF, 3f/30f, true);
		
		addPlayerSkinAnimation("lol_idle.png", Stickman_Animation.IDLE, Stickman_Skin.LOL, 4f/30f, false);
		addPlayerSkinAnimation("lol_lift_off.png", Stickman_Animation.LIFT_OFF, Stickman_Skin.LOL, 2f/30f, false);
		addPlayerSkinAnimation("lol_walking.png", Stickman_Animation.WALKING, Stickman_Skin.LOL, 3f/30f, false);
		addPlayerSkinAnimation("lol_mid_flight.png", Stickman_Animation.JUMPING, Stickman_Skin.LOL, 30f/30f, false);
		addPlayerSkinAnimation("lol_lift_off.png", Stickman_Animation.LANDING, Stickman_Skin.LOL, 3f/30f, true);
		
		this.portal = createAnimation("portal.png", 4f/30f, 800);
	}
	
	/**
	 * Adds a player animation in normal playback
	 * @param source - File path
	 * @param animName - animation code
	 * @param frameTime - duration of each frame
	 */
	private void addPlayerAnimation(String source, Stickman_Animation animName, float frameTime) {
		addPlayerAnimation(source, animName, frameTime, false);
	}
	
	/**
	 * Adds a player animation with specified playback frame order
	 * @param source - File path
	 * @param animName - animation code
	 * @param frameTime - duration of each frame
	 * @param reversePlayback - whether or not it reverses the playback
	 */
	private void addPlayerAnimation(String source, Stickman_Animation animName, float frameTime, boolean reversePlayback) {
		Texture t = new Texture(source);
		Texture tReversed = new Texture(source);
		
		
		TextureRegion[][] tr = TextureRegion.split(t, 128, 128);
		TextureRegion[][] trReversed = TextureRegion.split(tReversed, 128, 128);
		
		for(TextureRegion f : trReversed[0]) {
			f.flip(true, false);
		}
		
		
		TextureRegion[] frames = new TextureRegion[tr[0].length];
		System.arraycopy(tr[0], 0, frames, 0, tr[0].length); //copies one line of the texture region (all player animations are in one line)
		if(reversePlayback) {
			reverseTextureRegionArray(frames);
		}
		TextureRegion[] framesReversed = new TextureRegion[trReversed[0].length];
		System.arraycopy(trReversed[0], 0, framesReversed, 0, trReversed[0].length);
		
	    stickmanAnimations.put(new Pair<Stickman_Animation, Stickman_Facing_Direction>(
	    		animName, Stickman_Facing_Direction.RIGHT),
	    		new Animation<TextureRegion>(frameTime, frames));
	    
	    stickmanAnimations.put(new Pair<Stickman_Animation, Stickman_Facing_Direction>(
	    		animName, Stickman_Facing_Direction.LEFT),
	    		new Animation<TextureRegion>(frameTime, framesReversed));   
	}
	
	private void addPlayerSkinAnimation(String source, Stickman_Animation animName, Stickman_Skin skin, float frameTime, boolean reversePlayback) {
		Texture t = new Texture(source);
		Texture tReversed = new Texture(source);
		
		
		TextureRegion[][] tr = TextureRegion.split(t, 128, 128);
		TextureRegion[][] trReversed = TextureRegion.split(tReversed, 128, 128);
		
		for(TextureRegion f : trReversed[0]) {
			f.flip(true, false);
		}
		
		
		TextureRegion[] frames = new TextureRegion[tr[0].length];
		System.arraycopy(tr[0], 0, frames, 0, tr[0].length); //copies one line of the texture region (all player animations are in one line)
		if(reversePlayback) {
			reverseTextureRegionArray(frames);
		}
		TextureRegion[] framesReversed = new TextureRegion[trReversed[0].length];
		System.arraycopy(trReversed[0], 0, framesReversed, 0, trReversed[0].length);
		
	    stickmanSkinAnimations.put(new StickmanVisualDetails(
	    		animName, 
	    		Stickman_Facing_Direction.RIGHT,
	    		skin),
	    		new Animation<TextureRegion>(frameTime, frames));
	    
	    stickmanSkinAnimations.put(new StickmanVisualDetails(
	    		animName, 
	    		Stickman_Facing_Direction.LEFT,
	    		skin),
	    		new Animation<TextureRegion>(frameTime, framesReversed));
	}
	
	private Animation<TextureRegion> createAnimation(String source, float frameTime, int frameWidth) {
		Texture t = new Texture(source);
		
		TextureRegion[][] tr = TextureRegion.split(t, frameWidth, 128);
		
		
		TextureRegion[] frames = new TextureRegion[tr[0].length];
		System.arraycopy(tr[0], 0, frames, 0, tr[0].length); //copies one line of the texture region (all player animations are in one line)
		
	    return new Animation<TextureRegion>(frameTime, frames);
	    
	}
	
	private void reverseTextureRegionArray(TextureRegion[] array) {
		for(int i = 0; i < array.length / 2; i++)
		{
		    TextureRegion tmp = array[i];
		    array[i] = array[array.length-i-1];
		    array[array.length-i-1] = tmp;
		}
	}
	
	/**
	 * 
	 * @param anim - the type of animation
	 * @param direction - the facing direction
	 * @return the animation relative to the given parameters
	 */
	public Animation<TextureRegion> getStickmanAnimation(Stickman_Animation anim, Stickman_Facing_Direction direction) {
		Pair<Stickman_Animation, Stickman_Facing_Direction> animationProps = new Pair<Stickman_Animation, Stickman_Facing_Direction>(anim, direction);
		return stickmanAnimations.get(animationProps);
	}

	/**
	 * 
	 * @param anim - the type of animation
	 * @param direction - the facing direction
	 * @param skin - the desired skin
	 * @return the animation relative to the given parameters
	 */
	public Animation<TextureRegion> getStickmanSkinAnimation(Stickman_Animation anim, Stickman_Facing_Direction direction, Stickman_Skin skin) {
		return stickmanSkinAnimations.get(new StickmanVisualDetails(anim, direction, skin));
	}	
	
	/**
	 * 
	 * @return the game background texture
	 */
	public Texture getBackground() {
		return this.background;
	}

	/**
	 * 
	 * @return the cursor texture
	 */
	public Texture getCursor() {
		return this.cursor;
	}

	/**
	 * 
	 * @return the definitive line texture
	 */
	public Texture getLineTexture() {
		return this.lineTexture;
	}
	
	/**
	 * 
	 * @return the preview line texture
	 */
	public Texture getLinePreview() {
		return this.linePreview;
	}
	
	/**
	 * 
	 * @param texture the texture type
	 * @return specified GUI texture
	 */
	public Texture getGUITexture(GUI_Texture texture){
		return guiTextures.get(texture);
	}
	
	private void populateGUITextures(){
        guiTextures.put(GUI_Texture.LOGO, new Texture("logo.png"));
        guiTextures.put(GUI_Texture.CROSS, new Texture("cross.png"));
        guiTextures.put(GUI_Texture.TICK, new Texture("tick.png"));
    }
	
    private void createLabelStyle(){
        labelStyle = new Label.LabelStyle(
                new BitmapFont(Gdx.files.internal("purisa.fnt")),
                Color.BLACK);
        labelStyle.font.getData().setScale(0.7f);
    }
    
    private void createSubTextStyle(){
        subTextStyle = new Label.LabelStyle(
                new BitmapFont(Gdx.files.internal("purisa.fnt")),
                Color.BLACK);
        subTextStyle.font.getData().setScale(0.2f);
    }
    
    /**
     * 
     * @return the normal label style
     */
    public LabelStyle getLabelStyle() {
    	return labelStyle;
    }
    
    /**
     * 
     * @return the subtext label style
     */
    public LabelStyle getSubTextStyle() {
    	return subTextStyle;
    }
    
    /**
     * 
     * @param color the string color descriptor
     * @return the Color object
     */
    public static Color getColorFromString(String color) {
    	switch(Player_Color.valueOf(color)) {
    	case BLUE:
    		return new Color(20f/255f, 160f/255f , 1, 1);
    	default:
    		return new Color(1, 160f/255f, 20f/255f, 1);
    	}
    }
    
    /**
     * 
     * @return the portal animation
     */
    public Animation<TextureRegion> getPortalAnimation() {
    	return portal;
    }
    
    /**
     * 
     * @return the ink jar texture
     */
    public Texture getInkJar() {
    	return inkJar;
    }
    
    public Texture getCountdownBackground() {
    	return backgroundCountdown;
    }
    
    public Texture getInkBar() {
    	return inkBar;
    }
}
