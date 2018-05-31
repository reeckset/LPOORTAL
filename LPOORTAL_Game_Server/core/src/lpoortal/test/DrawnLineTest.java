package lpoortal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

import model.entities.DrawnLineModel;
import model.entities.EntityModel.ModelType;

public class DrawnLineTest {

	@Test
	public void createLineTest() {
	
		DrawnLineModel line1 = new DrawnLineModel(1, 1, 2, (float) (1 + Math.sqrt(3)));
		
		assertEquals(1, line1.getXi(), 0.1);
		assertEquals(1, line1.getYi(), 0.1);

		assertEquals(2, line1.getXf(), 0.1);
		assertEquals(1+Math.sqrt(3), line1.getYf(), 0.1);

		
		assertEquals(Math.PI/3, line1.getAngle(), 0.1);
		assertEquals(1, line1.getXLength(), 0.1);
		assertEquals(Math.sqrt(3), line1.getYLength(), 0.1);
		assertEquals(2, line1.getLength(), 0.1);
		
		
		DrawnLineModel line2 = new DrawnLineModel(2, 1, 1, 2);
		
		assertEquals(2, line2.getXi(), 0.1);
		assertEquals(1, line2.getYi(), 0.1);

		assertEquals(1, line2.getXf(), 0.1);
		assertEquals(2, line2.getYf(), 0.1);

		
		assertEquals(-Math.PI/4, line2.getAngle(), 0.1);
		assertEquals(1, line2.getXLength(), 0.1);
		assertEquals(1, line2.getYLength(), 0.1);
		assertEquals(Math.sqrt(2), line2.getLength(), 0.1);
		
	}
	
	
	
	@Test
	public void typeTest() {
		DrawnLineModel line = new DrawnLineModel(0, 0, 1, 1);
		
		assertEquals(ModelType.LINE, line.getType());
	}
	
	@Test
	public void previewTest() {
		DrawnLineModel line = new DrawnLineModel(0, 0, 1, 1);
		
		assertEquals(true, line.isPreview());
		
		line.setDefinitive();
		
		assertEquals(false, line.isPreview());
		
	}

}
