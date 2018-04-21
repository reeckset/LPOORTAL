package model;

public class PhoneController {

	static enum Functionality_State {
		MOVEMENT_CONTROLLER,
		DRAWING_CONTROLLER
	}
	
	protected Functionality_State state;
	
	public PhoneController(PhoneController.Functionality_State initialState) throws IllegalArgumentException{
		if(initialState == null) {
			throw new IllegalArgumentException("null initial state");
		}
		this.state = initialState;
	}

}
