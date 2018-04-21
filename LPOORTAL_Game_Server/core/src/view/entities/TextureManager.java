package view.entities;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import utilities.Pair;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;

public class TextureManager {
	
	
	private HashMap<Pair<Stickman_Animation, Stickman_Facing_Direction>, Animation<TextureRegion>> stickmanAnimations;
	private Texture background, cursor, lineTexture;
	
	public TextureManager(){
		stickmanAnimations = new HashMap<Pair<Stickman_Animation,Stickman_Facing_Direction>, Animation<TextureRegion>>();
		populateStaticTextures();
		populateAnimations();
	}
	
	/**
	 * Populates the static textures (non-animated)
	 */
	private void populateStaticTextures() {
		this.background = new Texture("background.jpg");
		this.cursor = new Texture("pencil.png");
		this.lineTexture = new Texture("Cowboy_Hat.png");
		
	}

	private void populateAnimations() {
		addPlayerAnimation("Idle.png", Stickman_Animation.IDLE, (int) (4f/30f));
		addPlayerAnimation("Lift_Off.png", Stickman_Animation.LIFT_OFF, (int) (2f/30f));
		addPlayerAnimation("Walking.png", Stickman_Animation.WALKING, (int) (3f/30f));
		addPlayerAnimation("mid_flight.png", Stickman_Animation.JUMPING, (int) (30f/30f));
	}

	private void addPlayerAnimation(String source, Stickman_Animation animName, int frameTime) {
		
		Texture t = new Texture(source);
		Texture tReversed = new Texture(source);
		
		
		TextureRegion[][] tr = TextureRegion.split(t, 128, 128);
		TextureRegion[][] trReversed = TextureRegion.split(tReversed, 128, 128);
		
		for(TextureRegion f : trReversed[0]) {
			f.flip(false, true);
		}
		
		TextureRegion[] frames = new TextureRegion[tr[0].length];
		System.arraycopy(tr[0], 0, frames, 0, tr[0].length);
		
		TextureRegion[] framesReversed = new TextureRegion[trReversed[0].length];
		System.arraycopy(trReversed[0], 0, framesReversed, 0, trReversed[0].length);
		
	    stickmanAnimations.put(new Pair<Stickman_Animation, Stickman_Facing_Direction>(
	    		animName, Stickman_Facing_Direction.RIGHT),
	    		new Animation<TextureRegion>(frameTime, frames));
	    
	    stickmanAnimations.put(new Pair<Stickman_Animation, Stickman_Facing_Direction>(
	    		animName, Stickman_Facing_Direction.LEFT),
	    		new Animation<TextureRegion>(frameTime, framesReversed));   
	}
	
	public Animation<TextureRegion> getStickmanAnimation(Stickman_Animation anim, Stickman_Facing_Direction direction) {
		Pair<Stickman_Animation, Stickman_Facing_Direction> animationProps = new Pair<Stickman_Animation, Stickman_Facing_Direction>(anim, direction);
		return stickmanAnimations.get(animationProps);
	}

	public Texture getBackground() {
		return this.background;
	}

	public Texture getCursor() {
		return this.cursor;
	}

	public Texture getLineTexture() {
		return this.lineTexture;
	}
}