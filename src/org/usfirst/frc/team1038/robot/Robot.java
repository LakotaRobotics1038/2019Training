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
	public static RobotDriveTrain robotDriveTrain = RobotDriveTrain.getInstance();
	public CommandGroup group = new CommandGroup();
	
	RobotSparkMotor firstMotor;
	JoystickCourtney firstJoystick;
	RobotSparkMotor secondMotor;
	Compressor airCompressor;
	
	public void robotInit() {
		firstMotor = new RobotSparkMotor(0);
		firstJoystick = new JoystickCourtney(0);
		secondMotor = new RobotSparkMotor(1);
		airCompressor = new Compressor(0);
		airCompressor.setClosedLoopControl(true);
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
		group.addSequential(new RobotDriveStraight(2));
	}
}
