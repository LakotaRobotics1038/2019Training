package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;

public class RobotDriveStraight extends PIDCommand {
	
	private final static double dP = 0.150;
	private final static double dI = 0.000;
	private final static double dD = 0.002;
	private PIDController drivePID = getPIDController();

	public RobotDriveStraight(double setPoint) {
		super(dP, dI, dD);
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
