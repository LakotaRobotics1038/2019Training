package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc.team1038.robot.Robot;

public class RobotDriveStraight extends PIDCommand {
	
	private final double stopDriveSpeed = 0.0;
	private final double stopDriveRotation = 0.0;
	private final double tolerance = 1.9;
	private final double maxOutput = .8;
	private final static double dP = 0.150;
	private final static double dI = 0.000;
	private final static double dD = 0.002;
	private final static double tP = 0.200;
	private final static double tI = 0.001;
	private final static double tD = 0.000;
	private RobotGyro gyroSensor = RobotGyro.getInstance();
	private RobotDriveTrain driveTrain = RobotDriveTrain.getInstance();
	private PIDController turnPID = new PIDController(tP, tI, tD, gyroSensor, Robot.emptySpark);
	private PIDController drivePID = getPIDController();

	public RobotDriveStraight(double setpoint) {
		super(dP, dI, dD);
		setSetpoint(setpoint *12);
		drivePID.setAbsoluteTolerance(tolerance);
		drivePID.setOutputRange(-maxOutput, maxOutput);
		drivePID.setContinuous(false);
		turnPID.setAbsoluteTolerance(tolerance);
		turnPID.setOutputRange(-maxOutput, maxOutput);
		turnPID.setInputRange(0, 360);
		turnPID.setContinuous(true);
		requires(/*HEEEEELLLLLLLP*/);
	}
	
	public void initialize() {
		turnPID.setSetpoint(gyroSensor.getAngle());
		driveTrain.resetEncoders();
	}
	
	public void execute() {
		drivePID.enable();
		turnPID.enable();
		double distancePID = drivePID.get();
		double anglePID = turnPID.get();
		usePIDOutput(distancePID, anglePID);
	}
	
	public void interrupted() {
		end();
		System.out.println("DriveStraight interrupted");
	}
	
	public void end() {
		drivePID.reset();
		turnPID.reset();
		driveTrain.drive(stopDriveSpeed, stopDriveRotation);
		System.out.println("DriveStraight ended");
	}

	@Override
	protected boolean isFinished() {
		return drivePID.onTarget() && turnPID.onTarget();
	}
	
	@Override
	protected double returnPIDInput() {
		return firstEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
	}
	
	protected void usePIDOutput(double drivePower, double turnPower) {
		driveTrain.dualArcadeDrive(drivePower, turnPower);
	}
}
