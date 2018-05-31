package lpoortal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

import model.entities.PortalModel;
import model.entities.EntityModel.ModelType;

public class PortalTest {

	
	@Test
	public void colorTest() {
		PortalModel portal = new PortalModel(0, 0);
		
		Color testColor = new Color(1,2,3,1);
		
		portal.setColor(testColor);
		
		
		assertEquals(testColor, portal.getColor());
		
	}
	
	@Test
	public void typeTest() {
		PortalModel portal = new PortalModel(0, 0);
		
		assertEquals(ModelType.PORTAL, portal.getType());
	}

}
