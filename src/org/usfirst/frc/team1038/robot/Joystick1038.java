package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Joystick1038 extends Joystick {
	
	private final int X_BUTTON = 1;
	private final int A_BUTTON = 2;
	private final int B_BUTTON = 3;
	private final int Y_BUTTON = 4;
	private final int LEFT_BUTTON = 5;
	private final int RIGHT_BUTTON = 6;
	private final int LEFT_TRIGGER = 7;
	private final int RIGHT_TRIGGER = 8;
	private final int BACK_BUTTON = 9;
	private final int START_BUTTON = 10;
	private final int LEFT_JOYSTICK_CLICK = 11;
	private final int RIGHT_JOYSTICK_CLICK = 12;
	
	private final int LEFT_JOYSTICK_HORIZONTAL = 0;
	private final int LEFT_JOYSTICK_VERTICAL = 1;
	private final int RIGHT_JOYSTICK_HORIZONTAL = 2;
	private final int RIGHT_JOYSTICK_VERTICAL = 3;

	public Joystick1038 (int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	public boolean getXButton() {
		return getRawButton(X_BUTTON);
	}
	
	public boolean getAButton() {
		return getRawButton(A_BUTTON);
	}
	
	public boolean getBButton() {
		return getRawButton(B_BUTTON);
	}
	
	public boolean getYButton() {
		return getRawButton(Y_BUTTON);
	}
	
	public boolean getLeftButton() {
		return getRawButton(LEFT_BUTTON);
	}
	
	public boolean getRightButton() {
		return getRawButton(RIGHT_BUTTON);
	}
	
	public boolean getLeftTrigger() {
		return getRawButton(LEFT_TRIGGER);
	}
	
	public boolean getRightTrigger() {
		return getRawButton(RIGHT_TRIGGER);
	}
	
	public boolean getBackButton() {
		return getRawButton(BACK_BUTTON);
	}
	
	public boolean getStartButton() {
		return getRawButton(START_BUTTON);
	}
	
	public boolean getLeftJoystickClick() {
		return getRawButton(LEFT_JOYSTICK_CLICK);
	}
	
	public boolean getRightJoystickClick() {
		return getRawButton(RIGHT_JOYSTICK_CLICK);
	}
	
	public double getLeftJoystickVertical() {
		return getRawAxis(LEFT_JOYSTICK_VERTICAL) * -1;
	}
	
	public double getLeftJoystickHorizontal() {
		return getRawAxis(LEFT_JOYSTICK_HORIZONTAL);
	}
	
	public double getRightJoystickVertical() {
		return getRawAxis(RIGHT_JOYSTICK_VERTICAL) * -1;
	}
	
	public double getRightJoystickHorizontal() {
		return getRawAxis(RIGHT_JOYSTICK_HORIZONTAL);
	}
}