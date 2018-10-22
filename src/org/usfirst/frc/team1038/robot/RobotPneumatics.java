package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SensorBase;

public class RobotPneumatics extends DoubleSolenoid{

	private boolean isHighGear = false;
	
	public RobotPneumatics(int forwardChannel, int reverseChannel) {
		super(SensorBase.getDefaultSolenoidModule(), forwardChannel, reverseChannel);
	}
	
	public void toggleGear() {
		if(isHighGear) {
			lowGear();
		}
		else {
			highGear();
		}
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
}
