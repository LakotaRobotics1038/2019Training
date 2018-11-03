package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.Joystick;

public class BestJoystick extends Joystick {

	private final int LEFT_JOYSTICK_HORIZONTAL = 0;
	private final int LEFT_JOYSTICK_VERTICAL = 1;
	private final int RIGHT_JOYSTICK_HORIZONTAL = 2;
	private final int RIGHT_JOYSTICK_VERTICAL = 3;
	
	public BestJoystick(int port) {
		super(port);
	}
	
	public double getLeftJoystickHorizontal () {
		
		return getRawAxis(LEFT_JOYSTICK_HORIZONTAL);
		
	}
public double getLeftJoystickVertical () {
		
		return getRawAxis(LEFT_JOYSTICK_VERTICAL);
		
	}
public double getRightJoystickVertical () {
	
	return getRawAxis(RIGHT_JOYSTICK_VERTICAL);
	
}
public double getRightJoystickHorizontal () {
	
	return getRawAxis(RIGHT_JOYSTICK_HORIZONTAL);
	
}
}
