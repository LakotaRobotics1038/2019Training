package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Joystick;

public class BestJoystick extends Joystick {

	private final int leftJoystickHorizontal = 0;
	private final int leftJoystickVertical = 1;
	private final int rightJoystickHorizontal = 2;
	private final int rightJoystickVertical = 3;
	
	public BestJoystick(int port) {
		super(port);
	}
	
	public double getLeftJoystickHorizontal () {
		
		return getRawAxis(leftJoystickHorizontal);
		
	}
public double getLeftJoystickVertical () {
		
		return getRawAxis(leftJoystickVertical);
		
	}
public double getRightJoystickVertical () {
	
	return getRawAxis(rightJoystickVertical);
	
}
public double getRightJoystickHorizontal () {
	
	return getRawAxis(rightJoystickHorizontal);
	
}
}
