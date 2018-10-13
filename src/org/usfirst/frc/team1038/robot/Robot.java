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
	
	TestingThatThing sparkTest;
	JoystickCourtney firstJoystick;
	TestingThatThing secondMotor;
	
	public void robotInit() {
		sparkTest = new TestingThatThing(0);
		firstJoystick = new JoystickCourtney(0);
		secondMotor = new TestingThatThing(1);
	}
	
	public void teleopPeriodic() {
		sparkTest.set(firstJoystick.getLeftJoystickVertical() * .5);
		secondMotor.set(firstJoystick.getRightJoystickVertical() * .5);
	}
}
