package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickLandis extends Joystick {
	
	private final int X = 1;
	private final int A = 2;
	private final int B = 3;
	private final int Y = 4;
	private final int leftB = 5;
	private final int rightB = 6;
	private final int leftT = 7;
	private final int rightT = 8;
	private final int back = 9;
	private final int start = 10;
	private final int leftJC = 11;
	private final int rightJC = 12;
	
	private final int leftJH = 1;
	private final int leftJV = 1;
	private final int rightJH = 1;
	private final int rightJV = 1;
	
	public JoystickLandis(int port) {
		super(port);
	}
	
	public boolean getX() {
		return getRawButton(X);
	}
	
	public boolean getA() {
		return getRawButton(A);
	}
	
	public boolean getB() {
		return getRawButton(B);
	}
	
	public boolean getY() {
		return getRawButton(Y);
	}
	
	public boolean getLeftB() {
		return getRawButton(leftB);
	}
	
	public boolean getRightB() {
		return getRawButton(rightB);
	}
	
	public boolean getLeftT() {
		return getRawButton(leftT);
	}
	
	public boolean getRightT() {
		return getRawButton(rightT);
	}
	
	public boolean getBack() {
		return getRawButton(back);
	}
	
	public boolean getStart() {
		return getRawButton(start);
	}
	
	public boolean getLeftJC() {
		return getRawButton(leftJC);
	}
	
	public boolean getRightJC() {
		return getRawButton(rightJC);
	}
	
	public boolean getLeftJV() {
		return getRawButton(leftJV) * -1;
	}
	
	public boolean getLeftJH() {
		return getRawButton(leftJH);
	}
	
	public boolean getRightJV() {
		return getRawButton(rightJV) * -1;
	}
	
	public boolean getRightJH() {
		return getRawButton(rightJH);
	}
}
