package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DrivePIDstyle extends PIDCommand {

	
	private final static double dP = 0.150; //.04 limo
	private final static double dI = 0.000;
	private final static double dD = 0.002;
	private PIDController drivePID = getPIDController();
	public static final double TOLERANCE = 20;
	
	private Spark jeb;
	private Encoder eman;
	
	public DrivePIDstyle(double target, Spark j, Encoder e) {
		super(dP,dI,dD);
		
		
		setSetpoint(target); // guys GUYS please  PLEASE  don't call your argument "setpoint" if your function name is "setSetpoint"
		drivePID.setAbsoluteTolerance(TOLERANCE);
		drivePID.setOutputRange(-.75, .75); //if you stress the motor too much it quits the job
		drivePID.setContinuous(false); // what does this do
		
		jeb      =j;
		eman     =e;
		System.out.println("Dri]vePIDstyle constructor executed");
		requires(Robot.robotDrive); // what
		
	}
	
	@Override // does this ting
	public void initialize () {
		eman.reset(); //gang
		System.out.println("initialize() e xecuted");
		
	}

	public void execute ()  {
		System.out.println("execute() executed");
		drivePID.enable();
		usePIDOutput(drivePID.get());
		
	}
	@Override
	protected double returnPIDInput() {
		return eman.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		jeb.setSpeed(output);
		
	}

	@Override
	protected boolean isFinished() {
		return drivePID.onTarget(); // stolen from drive straight command dot jave
	}
	
	
	@Override
	public void interrupted() {
		end();
		System.out.println("wake me up inside");
	}
	
	
	
	
}
