package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RobotDriveTrain extends Subsystem {
	
	private final int firstEncoderPort1 = 0;
	private final int firstEncoderPort2 = 1;
	private final int secondEncoderPort1 = 3;
	private final int secondEncoderPort2 = 4;
	private final int countsPerRevolution = 205;
	private final int wheelDiameter = 6;
	RobotEncoder firstEncoder = new RobotEncoder(firstEncoderPort1, firstEncoderPort2, countsPerRevolution, wheelDiameter);
	RobotEncoder secondEncoder = new RobotEncoder(secondEncoderPort1, secondEncoderPort2, countsPerRevolution, wheelDiameter);
	private final int firstLeftMotorPort = 10;
	private final int firstRightMotorPort = 12;
	RobotSparkMotor firstLeftMotor = new RobotSparkMotor(firstLeftMotorPort);
	RobotSparkMotor firstRightMotor = new RobotSparkMotor(firstRightMotorPort);
	private boolean isHighGear = false;
	private boolean isPTO = false;
	RobotPneumatics shifter = new RobotPneumatics(2, 3);
	RobotPneumatics PTOShifter = new RobotPneumatics(0, 1);
	private DifferentialDrive differentialDrive;
	private static RobotDriveTrain driveTrain;
	public enum driveModes { tankDrive, singleArcadeDrive, dualArcadeDrive };
	public driveModes currentDriveMode = driveModes.dualArcadeDrive;
	public driveModes prevDriveMode = currentDriveMode;
	
	public RobotDriveTrain() {
		differentialDrive = new DifferentialDrive(firstLeftMotor, firstRightMotor);
	}
	
	public void resetEncoders() {
		firstEncoder.reset();
		secondEncoder.reset();
	}
	
	switch ()
	
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

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
