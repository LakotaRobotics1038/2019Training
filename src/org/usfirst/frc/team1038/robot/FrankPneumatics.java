package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SensorBase;

public class FrankPneumatics extends DoubleSolenoid{

	private boolean isHighGear = false;
	private boolean isPTO = false;
	static FrankPneumatics PTOshifter;
	static FrankPneumatics shifter;
	
	public static FrankPneumatics getFirstInstance() {
		if (shifter == null) {
			shifter = new FrankPneumatics(2, 3);
		}
		return shifter;
	}
	
	public static FrankPneumatics getInstance() {
		if (PTOshifter == null) {
			PTOshifter = new FrankPneumatics(0, 1);
		}
		return PTOshifter;
	}
	
	public FrankPneumatics(int forwardChannel, int reverseChannel) {
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