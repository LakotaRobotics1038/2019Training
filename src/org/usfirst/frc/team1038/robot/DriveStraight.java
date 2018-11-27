package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand{
	private static final double P = 0.01;
	private static final double I = 0.01;
	private static final double D = 0.01;
	private DriveTrain robotDriveTrain = DriveTrain.getInstance();
	private PIDController drivePIDLeft = getPIDController();
	private PIDController drivePIDRight = getPIDController();
	private static final double endDriveSpeed = 0.0;
	
	public DriveStraight() {
		super(P, I, D);
		drivePIDLeft.setAbsoluteTolerance(0.2);
		drivePIDLeft.setContinuous(false);
		drivePIDRight.setAbsoluteTolerance(0.2);
		drivePIDRight.setContinuous(false);
		requires(robotDriveTrain);
	}
	
	public void initialize() {
		robotDriveTrain.resetEncoders();
	}
	
	public void execute() {
		drivePIDLeft.enable();
		double leftDistancePID = drivePIDLeft.get();
		drivePIDRight.enable();
		double rightDistancePID = drivePIDRight.get();
		System.out.println(leftDistancePID + " " + rightDistancePID);
		usePIDOutput(leftDistancePID, rightDistancePID);
	}
	
	@Override
	public void interrupted() {
		end();
	}
	
	@Override
	public void end() {
		drivePIDLeft.reset();
		drivePIDRight.reset();
		robotDriveTrain.tankDrive(endDriveSpeed, endDriveSpeed);
	}

	@Override
	protected double returnPIDInput() {
		return robotDriveTrain.getLeftDriveEncoderDistance();
	}
	
	private void usePIDOutput(double leftDrivePower, double rightDrivePower) {
		robotDriveTrain.tankDrive(leftDrivePower, rightDrivePower);		//leftDrivePower = leftDistancePID   rightDrivePower = rightDistancePID
	}
	
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return drivePIDLeft.onTarget() && drivePIDRight.onTarget();
	}
}
