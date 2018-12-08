package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SensorBase;

public class Pneumatics1038 extends DoubleSolenoid{

	public Pneumatics1038(int forwardChannel, int reverseChannel) {
		super(forwardChannel, reverseChannel);
		// TODO Auto-generated constructor stub
	}
private Boolean isHighGear = false;
private Boolean isPTO = false;
static Pneumatics1038 PTOshifter;
static Pneumatics1038 shifter;

public static Pneumatics1038 getFirstInstance() {
	if (shifter == null) {
		shifter = new Pneumatics1038(2,3);

	}
	return shifter;
	
}
public static Pneumatics1038 getInstance() {
	if(PTOshifter == null) {
		PTOshifter = new Pneumatics1038(0,1);
	}
	return PTOshifter;
}
//public Pneumatics1038(int forwardChannel, int reverseChannel) {
//	super(SensorBase.getDefaultSolenoidModule(), forwardChannel, reverseChannel);
//}
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
