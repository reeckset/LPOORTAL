package lpoortal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

import model.entities.EntityModel.ModelType;
import model.entities.StickmanModel;
import model.entities.StickmanModel.Stickman_Animation;
import model.entities.StickmanModel.Stickman_Facing_Direction;
import view.entities.StickmanVisualDetails.Stickman_Skin;

public class StickmanTest {

	
	
	@Test
	public void moveTest() {
	
		StickmanModel stickman = new StickmanModel(0, 0);
		
		stickman.setPosition(1, 1);
		assertEquals(1, stickman.getX(), 0.1);
		assertEquals(1, stickman.getY(), 0.1);
	

		stickman.setPosition(-1, -1);
		assertEquals(-1, stickman.getX(), 0.1);
		assertEquals(-1, stickman.getY(), 0.1);
		
		stickman.setPosition(0, 0);
		assertEquals(0, stickman.getX(), 0.1);
		assertEquals(0, stickman.getY(), 0.1);
	}
	
	@Test
	public void jumpTest() {
	
		StickmanModel stickman = new StickmanModel(0, 0);
		
		assertEquals(false, stickman.isJumping());
		
		stickman.setJumping(true);
		assertEquals(true, stickman.isJumping());
		
		stickman.setJumping(false);
		assertEquals(false, stickman.isJumping());
		
	}
	
	@Test
	public void skinTest() {
		StickmanModel stickman = new StickmanModel(0, 0);
		
		stickman.setSkin(Stickman_Skin.LOL);
		
		assertEquals(Stickman_Skin.LOL, stickman.getSkin());
		
		stickman.setSkin(Stickman_Skin.NINJA);
		
		assertEquals(Stickman_Skin.NINJA, stickman.getSkin());
		
	}
	
	@Test
	public void stateTest() {
		StickmanModel stickman = new StickmanModel(0, 0);
		
		assertEquals(Stickman_Animation.IDLE, stickman.getState());
		
		stickman.setState(Stickman_Animation.WALKING);
		
		assertEquals(Stickman_Animation.WALKING, stickman.getState());
		
		
	}
	
	@Test
	public void facingDirectionTest() {
		StickmanModel stickman = new StickmanModel(0, 0);
		
		assertEquals(Stickman_Facing_Direction.RIGHT, stickman.getFacingDirection());
		
		stickman.setFacingDirection(Stickman_Facing_Direction.LEFT);
		
		assertEquals(Stickman_Facing_Direction.LEFT, stickman.getFacingDirection());
		
	}
	
	@Test
	public void colorTest() {
		StickmanModel stickman = new StickmanModel(0, 0);
		
		Color testColor = new Color(1,2,3,1);
		
		stickman.setColor(testColor);
		
		
		assertEquals(testColor, stickman.getColor());
		
	}
	
	@Test
	public void typeTest() {
		StickmanModel stickman = new StickmanModel(0, 0);
		
		assertEquals(ModelType.STICKMAN, stickman.getType());
	}

}
