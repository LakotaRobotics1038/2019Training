package org.usfirst.frc.team1038.robot;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SensorBase;


public class PneumaticsA extends DoubleSolenoid{
	
	private boolean inHighGear = false;
	private boolean isPTO = false;
	static PneumaticsA PTOshifter;
	static PneumaticsA shifter;
	
	public PneumaticsA(int forwardChannel, int reverseChannel) {
		super(SensorBase.getDefaultSolenoidModule(), forwardChannel, reverseChannel);
	}
	
	public static PneumaticsA getInstance1() {
		if (shifter == null) {
			shifter = new PneumaticsA(2, 3);
		}
		return shifter;
	}
	
	public static PneumaticsA getInstance2() {
		if (PTOshifter == null) {
			PTOshifter = new PneumaticsA(0, 1);
		}
		return PTOshifter;
	}
	
	public void highGear() {
		inHighGear = true;
		this.set(DoubleSolenoid.Value.kForward);
	}
	
	public void lowGear() {
		inHighGear = false;
		this.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean inHighGear() {
		return inHighGear;
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
