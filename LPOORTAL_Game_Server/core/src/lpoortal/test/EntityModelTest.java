package lpoortal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entities.EntityModel;
import model.entities.EntityModel.ModelType;

public class EntityModelTest {

	@Test
	public void removeFlag() {
		EntityModel model = new EntityModel(0, 0) {
			
			@Override
			public ModelType getType() {
				return null;
			}
		};
		
		
		assertEquals(false, model.isFlaggedToBeRemoved());
		
		model.setFlaggedForRemoval(true);
		
		assertEquals(true, model.isFlaggedToBeRemoved());
	}

}
