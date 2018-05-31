package lpoortal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

import model.entities.InkJarModel;
import model.entities.EntityModel.ModelType;

public class InkJarTest {

	
	@Test
	public void typeTest() {
		InkJarModel inkJar = new InkJarModel(0, 0);
		
		assertEquals(ModelType.INKJAR, inkJar.getType());
	}

}
