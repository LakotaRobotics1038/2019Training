package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PIDController;

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
	}
	
	public void execute() {
		drivePID.enable();
	}
	
	public void end() {
		drivePID.reset();
	}

	@Override
	protected double returnPIDInput() {
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
