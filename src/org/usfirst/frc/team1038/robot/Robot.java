/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	public Spark firstMotor = new Spark(0);
	public Spark secondMotor = new Spark(2);
	public Spark thirdMotor = new Spark(1);
	public FrankJoystick joystickController = new FrankJoystick(0);

	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	@Override
	public void teleopPeriodic() {
		String output = "" + joystickController.getLeftJoystickVertical() + " , " + joystickController.getLeftJoystickHorizontal() + " , " + joystickController.getRightJoystickVertical() + " , " + joystickController.getRightJoystickHorizontal() + " , " + joystickController.getXButton() + " , " + joystickController.getAButton() + " , " + joystickController.getBButton() + " , " + joystickController.getYButton() + " ";
		BufferedWriter fileWriter;
		System.out.println("its not gonna print this");
		try {
			File testFile = new File("new_directory/EDFL.txt");
			testFile.mkdirs();
			testFile.createNewFile();
			//File testFile = new File("/home/lvuser/Output.txt");
			//testFile.createNewFile();
			fileWriter = new BufferedWriter(new FileWriter(testFile, true));
			fileWriter.write(output);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void testPeriodic() {
	}
}