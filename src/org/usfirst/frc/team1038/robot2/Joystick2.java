package org.usfirst.frc.team1038.robot2;

import  edu.wpi.first.wpilibj.Joystick;

public class Joystick2 extends Joystick {

	public Joystick2(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	private final int Left_Horizontal = 0;
	private final int Left_Side = 1;
	private final int Right_Horizontal = 2;
	private final int Right_Side = 3;
	
	public double getLeftJoystickHorizontal() {
		
		return getRawAxis(Left_Horizontal);
	}
		
	public double getLeftJoysickVertical() {
		
		return getRawAxis(Left_Side);
	}
	
	public double getRightJoystickHorizontal() {
		
		return getRawAxis(Right_Horizontal);
	}
	
	public double getRightJoystickVertical() {
		
		return getRawAxis(Right_Side);
	}
}
