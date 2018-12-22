package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DrivePIDstyle extends PIDCommand {

	
	private final static double dPs1 = 0.13000; //.04 limo
	private final static double dIs1 = 0;
	private final static double dDs1 = 0.03;
	private final static double dPt = 0.13000; //.04 limo
	private final static double dIt= 0;
	private final static double dDt = 0.03;
	
	private PIDController drivePID = getPIDController();
	private I2CGyro gyroSensor = I2CGyro.getInstance();
	
	public static final double TOLERANCE = 0.25;
	
	private Spark jeb;
	private Encoder eman;
	
	public DrivePIDstyle(double target, Spark j, Encoder e) {
		super(dPs1,dIs1,dDs1);
		
		
		
		setSetpoint(target); //   don't call your argument "setpoint" if your function name is "setSetpoint"
		drivePID.setAbsoluteTolerance(TOLERANCE);
		drivePID.setOutputRange(-.75, .75); //if you stress the motor too much it quits the job
		drivePID.setContinuous(false); // ???
		
		jeb      =j;
		eman     =e;
		System.out.println(" Dri]vePIDstyle constructor executed");
		requires(Robot.robotDrive); // what
		
	}
	
	@Override // does this ting
	public void initialize () {
		System.out.println("initialize() e xecuted");
		
	}

	public void execute ()  {
		System.out.println("execute() executed");
		drivePID.enable();
		System.out.println("drivePID.enable()d");
		usePIDOutput(drivePID.get());
		System.out.println("drivePID.get()ted");
	}
	@Override
	protected double returnPIDInput() {
		return eman.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		
		jeb.setSpeed(-output);
		
		
	}

	@Override
	protected boolean isFinished() {
		return drivePID.onTarget(); // stolen from drive straight command. java
	}
	
	
	@Override
	public void interrupted() {
		end();
		System.out.println("wake me up inside");
	}
	
	
	
	
}
