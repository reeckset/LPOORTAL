package lpoortal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

import model.entities.CursorModel;
import model.entities.EntityModel.ModelType;

public class CursorTest {


	@Test
	public void moveTest() {
	
		CursorModel cursor = new CursorModel(0, 0);
		
		cursor.move(1, 1);
		assertEquals(1, cursor.getX(), 0.1);
		assertEquals(1, cursor.getY(), 0.1);
	

		cursor.move(-1, -1);
		assertEquals(-1, cursor.getX(), 0.1);
		assertEquals(-1, cursor.getY(), 0.1);
		
		cursor.move(0, 0);
		assertEquals(0, cursor.getX(), 0.1);
		assertEquals(0, cursor.getY(), 0.1);
	}
	
	@Test
	public void colorTest() {
		CursorModel cursor = new CursorModel(0, 0);
		
		Color testColor = new Color(1,2,3,1);
		
		cursor.setColor(testColor);
		
		
		assertEquals(testColor, cursor.getColor());
		
	}
	
	@Test
	public void typeTest() {
		CursorModel cursor = new CursorModel(0, 0);
		
		assertEquals(ModelType.CURSOR, cursor.getType());
	}

}
