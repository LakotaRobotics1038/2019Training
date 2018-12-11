/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

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
			try {
				FileReader fileReader = new FileReader("/home/lvuser/Output.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String fLine = bufferedReader.readLine();
				while (fLine != null) {
					ArrayList<String> lineArrLst = new ArrayList<>(Arrays.asList(fLine.split(",")));
					final int firstMotorSpeed = Integer.parseInt(lineArrLst.get(0));
					final int secondMotorSpeed = Integer.parseInt(lineArrLst.get(1));
					System.out.println(firstMotorSpeed + " , " + secondMotorSpeed);
					firstMotor.set(firstMotorSpeed);
					secondMotor.set(secondMotorSpeed);
					fLine = bufferedReader.readLine();
				}
				fileReader.close();
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e2) {
				System.out.println(e2);
			}
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	@Override
	public void teleopPeriodic() {
		File testFile;
		FileWriter fileWrite = null;
		BufferedWriter bufferedWrite;
		BufferedWriter fileWriter;
		//System.out.println("its definitely still gonna print the old one");
		try {
			testFile = new File("/home/lvuser/Output.txt");
			if(!testFile.exists()) {
				testFile.createNewFile();
			}
			fileWrite = new FileWriter(testFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedWrite = new BufferedWriter(fileWrite);
		try {
			for(int i=0; i < 1000; i++) {
				//String output = "" + joystickController.getLeftJoystickVertical() + " , " + joystickController.getLeftJoystickHorizontal() + " , " + joystickController.getRightJoystickVertical() + " , " + joystickController.getRightJoystickHorizontal() + " , " + joystickController.getXButton() + " , " + joystickController.getAButton() + " , " + joystickController.getBButton() + " , " + joystickController.getYButton() + "\r\n";
				//double joystickValue = joystickController.getLeftJoystickVertical() * .5;
				String output = "" + joystickController.getLeftJoystickVertical() + "," + joystickController.getRightJoystickVertical() + "\r\n";
				firstMotor.set(joystickController.getLeftJoystickVertical());
				secondMotor.set(joystickController.getRightJoystickVertical());
				System.out.println(output);
				System.out.println(java.time.LocalTime.now());
				bufferedWrite.write(output);
				System.out.println(java.time.LocalTime.now()); 
				Timer.delay(0.01);
				System.out.println(java.time.LocalTime.now()); 
				System.out.println(i);
			}
			bufferedWrite.flush();
			bufferedWrite.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void testPeriodic() {
	}
}