package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RobotDriveTrain extends Subsystem {
	
	RobotEncoder firstEncoder = RobotEncoder.getInstance();
	RobotEncoder secondEncoder = RobotEncoder.getFirstInstance();
	RobotSparkMotor firstMotor = RobotSparkMotor.getInstance();
	RobotSparkMotor secondMotor = RobotSparkMotor.getFirstInstance();
	private boolean isHighGear = false;
	private boolean isPTO = false;
	RobotPneumatics shifter = RobotPneumatics.getFirstInstance();
	RobotPneumatics PTOshifter = RobotPneumatics.getInstance();	
	private DifferentialDrive differentialDrive;
	static RobotDriveTrain driveTrain;
	public enum driveModes { tankDrive, singleArcadeDrive, dualArcadeDrive };
	public driveModes currentDriveMode = driveModes.dualArcadeDrive;
	public driveModes prevDriveMode = currentDriveMode;
	
	public static RobotDriveTrain getInstance() {
		if (driveTrain == null) {
			driveTrain = new RobotDriveTrain();
		}
		return driveTrain;
	}
	
	public RobotDriveTrain() {
		differentialDrive = new DifferentialDrive(firstMotor, secondMotor);
	}
	
	public int getLeftDriveEncoderCount() {
		return firstEncoder.getCount();
	}
	
	public int getRightDriveEncoderCount() {
		return secondEncoder.getCount();
	}
	
	public double getLeftDriveEncoderDistance() {
		return firstEncoder.getDistance();
	}
	
	public double getRightDriveEncoderDistance() {
		return secondEncoder.getDistance();
	}
	
	public void resetEncoders() {
		firstEncoder.reset();
		secondEncoder.reset();
	}
	
	public void changeDriveMode(driveModes currentDriveMode) {
		switch (currentDriveMode) {
		case tankDrive:
			prevDriveMode = currentDriveMode;
			currentDriveMode = driveModes.dualArcadeDrive;
			break;
		case dualArcadeDrive:
			currentDriveMode = driveModes.singleArcadeDrive;
			break;
		case singleArcadeDrive:
			currentDriveMode = driveModes.tankDrive;
			break;
		}
	}
	
	public void tankDrive(double leftJoystickValue, double rightJoystickValue) {
		if (isPTO) {
			differentialDrive.tankDrive(-Math.abs(leftJoystickValue), -Math.abs(leftJoystickValue), true);
		}
		else {
			differentialDrive.tankDrive(leftJoystickValue, rightJoystickValue, true);
		}
	}
	
	public void dualArcadeDrive(double xAxis, double yAxis) {
		if (isPTO) {
			differentialDrive.arcadeDrive(-Math.abs(yAxis), 0, true);
		}
		else {
			differentialDrive.arcadeDrive(yAxis * -1, xAxis, true);
		}
	}
	
	public void singleArcadeDrive(double turning, double speed) {
		if (isPTO) {
			differentialDrive.arcadeDrive(-Math.abs(speed), 0, true);
		}
		else {
			differentialDrive.tankDrive(speed, turning, true);
		}
	}
	
	public void drive(double speedValue, double rotationValue) {
		differentialDrive.curvatureDrive(speedValue, rotationValue, false);
	}
	
	public void highGear() {
		isHighGear = true;
		(RobotPneumatics.getFirstInstance()).set(DoubleSolenoid.Value.kForward);
	}
	
	public void lowGear() {
		isHighGear = false;
		(RobotPneumatics.getFirstInstance()).set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isHighGear() {
		return isHighGear;
	}
	
	public void PTOOn() {
		isPTO = true;
		(RobotPneumatics.getInstance()).set(DoubleSolenoid.Value.kForward);
	}
	
	public void PTOOff() {
		isPTO = false;
		(RobotPneumatics.getInstance()).set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isPTO() {
		return isPTO;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
