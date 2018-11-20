/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;

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
	private RobotPneumatics shifter = new RobotPneumatics(2, 3);
	private RobotPneumatics PTOShifter = new RobotPneumatics(0, 1);
	static RobotSparkMotor emptySpark = new RobotSparkMotor(9);
	public static RobotDriveTrain robotDriveTrain = RobotDriveTrain.getInstance();
	
	RobotSparkMotor firstMotor;
	JoystickCourtney firstJoystick;
	RobotSparkMotor secondMotor;
	public RobotEncoder firstEncoder;
	RobotEncoder secondEncoder;
	Compressor airCompressor;
	
	public void robotInit() {
		firstMotor = new RobotSparkMotor(0);
		firstJoystick = new JoystickCourtney(0);
		secondMotor = new RobotSparkMotor(1);
		firstEncoder = new RobotEncoder(0 , 1 , 207 , 6);
		secondEncoder = new RobotEncoder(2 , 3 , 207 , 6);
		airCompressor = new Compressor(0);
		airCompressor.setClosedLoopControl(true);
	}
	
	public void teleopPeriodic() {
		firstMotor.set(firstJoystick.getLeftJoystickVertical() * .5);
		secondMotor.set(firstJoystick.getRightJoystickVertical() * .5);
		System.out.println(firstEncoder.getCount() + " , " + secondEncoder.getCount());
		if(firstJoystick.getXButton()) {
			shifter.highGear();
		}
		if(firstJoystick.getYButton()) {
			shifter.lowGear();
		}
		if(firstJoystick.getAButton()) {
			PTOShifter.PTOOn();
		}
		if(firstJoystick.getBButton()) {
			PTOShifter.PTOOff();
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
		/*I need an object to call this on but idk what*/.addSequential(new RobotDriveStraight(2));
	}
}
