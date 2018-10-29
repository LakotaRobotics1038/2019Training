package org.usfirst.frc.team1038.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

public class TalonMotorController extends TalonSRX implements SpeedController {
	
	public TalonMotorController (int address) {
		super(address); 
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		set(output);
	}

	@Override
	public void set(double speed) {
		// TODO Auto-generated method stub
		super.set(super.getControlMode(), speed);
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return super.getMotorOutputPercent();
	}

	@Override
	public void setInverted(boolean isInverted) {
		// TODO Auto-generated method stub
		super.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return super.getInverted();
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		super.set(super.getControlMode(), 0);
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		super.set(super.getControlMode(), 0);
	}

}