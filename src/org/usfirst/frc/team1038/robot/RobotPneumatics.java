package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SensorBase;

public class RobotPneumatics extends DoubleSolenoid{

	private boolean isHighGear = false;
	private boolean isPTO = false;
	static RobotPneumatics PTOshifter;
	static RobotPneumatics shifter;
	
	public static RobotPneumatics getFirstInstance() {
		if (shifter == null) {
			shifter = new RobotPneumatics(2, 3);
		}
		return shifter;
	}
	
	public static RobotPneumatics getInstance() {
		if (PTOshifter == null) {
			PTOshifter = new RobotPneumatics(0, 1);
		}
		return PTOshifter;
	}
	
	public RobotPneumatics(int forwardChannel, int reverseChannel) {
		super(SensorBase.getDefaultSolenoidModule(), forwardChannel, reverseChannel);
	}
	
	public void highGear() {
		isHighGear = true;
		this.set(DoubleSolenoid.Value.kForward);
	}
	
	public void lowGear() {
		isHighGear = false;
		this.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isHighGear() {
		return isHighGear;
	}
	
	public void PTOOn() {
		isPTO = true;
		this.set(DoubleSolenoid.Value.kForward);
	}
	
	public void PTOOff() {
		isPTO = false;
		this.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isPTO() {
		return isPTO;
	}
}
