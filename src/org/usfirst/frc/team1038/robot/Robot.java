/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	static RobotSparkMotor emptySpark = new RobotSparkMotor(9);
	public static RobotDriveTrain driveTrain = RobotDriveTrain.getInstance();
	RobotEncoder firstEncoder = RobotEncoder.getInstance();
	RobotEncoder secondEncoder = RobotEncoder.getFirstInstance();
	RobotSparkMotor firstMotor = RobotSparkMotor.getInstance();
	RobotSparkMotor secondMotor = RobotSparkMotor.getFirstInstance();
	JoystickCourtney firstJoystick = new JoystickCourtney(0);
	Compressor airCompressor = new Compressor(0);
	Scheduler schedule = Scheduler.getInstance();
	
	public void robotInit() {
		airCompressor.setClosedLoopControl(true);
		schedule.add(new RobotDriveStraight(2));
	}
	
	public void teleopPeriodic() {
		firstMotor.set(firstJoystick.getLeftJoystickVertical() * .5);
		secondMotor.set(firstJoystick.getRightJoystickVertical() * .5);
		System.out.println(firstEncoder.getCount() + " , " + secondEncoder.getCount());
		if(firstJoystick.getXButton()) {
			(RobotPneumatics.getFirstInstance()).highGear();
		}
		if(firstJoystick.getYButton()) {
			(RobotPneumatics.getFirstInstance()).lowGear();
		}
		if(firstJoystick.getAButton()) {
			(RobotPneumatics.getInstance()).PTOOn();
		}
		if(firstJoystick.getBButton()) {
			(RobotPneumatics.getInstance()).PTOOff();
		}
	}
	
	public void autonomousPeriodic() {
		System.out.println(firstEncoder.getCount() + " , " + secondEncoder.getCount());
		System.out.println(firstEncoder.getDistance() + " , " + secondEncoder.getDistance());
		//to run motor for a distance:
		/*
		if(firstEncoder.getDistance() < 12) {
			firstMotor.set(0.7);
		}
		else {
			firstMotor.set(0);
		}
		if(secondEncoder.getDistance() < 12) {
			secondMotor.set(-0.7);
		}
		else {
			secondMotor.set(0);
		}
		*/
		if (schedule != null) {
			schedule.run();
		}
	}
}
