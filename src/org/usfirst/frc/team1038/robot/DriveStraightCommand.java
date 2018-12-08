package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraightCommand extends PIDCommand{

		// TODO Auto-generated constructor stub

public DriveStraightCommand(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}
private final double END_DRIVE_Speed = 0.0;
private final double END_DRIVE_ROTATION = 0.0;
private final double TOLERANCE = 1.9;
private final double MAX_OUTPUT = .8;
private final static double dP = 1.150;
private final static double dI = 0.0000;
private final static double dD = 0.002;
private PIDController drivePID =  getPIDController();
@Override
protected double returnPIDInput() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
protected void usePIDOutput(double output) {
	// TODO Auto-generated method stub
	
}
@Override
protected boolean isFinished() {
	// TODO Auto-generated method stub
	return false;
}


}