package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem { // i stole thi scode frpom lakota robotisc 2018 project

	
	private DifferentialDrive differentialDrive;
	private static DriveTrain driveTrain;
	
	private final static int LEFT_DRIVE_PORT_1 = 10;
	private final static int LEFT_DRIVE_PORT_2 = 11;
	private final static int RIGHT_DRIVE_PORT_1 = 12;
	private final static int RIGHT_DRIVE_PORT_2 = 13;
	public Spark leftDrive1 = new Spark(LEFT_DRIVE_PORT_1);
	private Spark	 leftDrive2 = new Spark(LEFT_DRIVE_PORT_2);
	public Spark rightDrive1 = new Spark(RIGHT_DRIVE_PORT_1);
	private Spark rightDrive2 = new Spark(RIGHT_DRIVE_PORT_2);
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stud

	}
	
	
	
	public static DriveTrain getInstance() {
		if (driveTrain == null) {
			System.out.println("Creating a new DriveTrain");
			driveTrain = new DriveTrain();
		}
		return driveTrain;
	}

	private DriveTrain() {
		//leftDrive1.setInverted(true);
		//rightDrive1.setInverted(true);
		//distPerPulse = Encoder1038.findDistancePerPulse(ENCODER_COUNTS_PER_REV, WHEEL_DIAMETER);
		//leftDrive2.follow(leftDrive1);
		//rightDrive2.follow(rightDrive1);  // this code doesn't work becasuse the talons died
		differentialDrive = new DifferentialDrive(leftDrive1, rightDrive1);
	}

}
