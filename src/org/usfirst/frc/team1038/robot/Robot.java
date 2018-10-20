/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

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
	public static final double WHEEL_DIAM = 6;
	public static final double COUNTS_PER_REV = 207;
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	private Spark spade, spade2;

	private Joystick sticc;
	
	private Encoder encodeman;
	
	private DoubleSolenoid ligmoid;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		spade = new Spark(0);
		spade2 = new Spark(1); // make this into an array or something lol
		sticc = new Joystick(0);
		encodeman = new Encoder(2,3);
		encodeman.reset();
		ligmoid = new DoubleSolenoid(0,1);
		

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}
	
	public void moveDist(double dist, double speed) {
		int curCounts = encodeman.get();
		while (Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get()-curCounts)) < dist) {
			spade2.set(speed);
		}
		spade2.set(0);
		
	}
	
	public void teleopInit() {
		encodeman.reset();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
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
		System.out.println(encodeman.get());
		/*if (Math.abs(encodeman.get()) < 3000) {
			spade2.set(-(encodeman.get()+4000)/7005.0);
			System.out.println((encodeman.get()+100)/405.0);
		} else {
			spade2.set(0);
			System.out.println("h");
		}*/
		
		double doDistance = 12;
		if (Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get())) >= doDistance)
			spade2.set(-0);
		else
			spade2.set(-1*Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get())-doDistance)/(doDistance*2)-.5);
			
		
		
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		if (!sticc.getRawButton(1)) {
			spade.set(sticc.getX()*1);
			spade2.set(sticc.getZ()*1);
		} else {
			spade.set(Math.sin(System.currentTimeMillis()*0.01)*0.5);
			spade2.set(Math.cos(System.currentTimeMillis()*0.01)*0.5);
		}
		System.out.println(encodeman.get());

	}
	

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		try {
		ligmoid.set(DoubleSolenoid.Value.kForward);
		ligmoid.wait(10);
		ligmoid.set(DoubleSolenoid.Value.kReverse);
		ligmoid.wait(10);
		ligmoid.setName("jeff");
		} catch (Exception minecraft) {
			;;;;;;;;;;
		}
	}

	public double bool2dub(boolean b) {
		if (b)
			return 0.4;
		return -0.4;
	}
	
	//return same unit as you put wheel diam lmao xdddddddd
	public double getDist(double countsPerRev, double wheelDiam, int totalCounts) {
		return ((double)totalCounts/countsPerRev*(wheelDiam*Math.PI));
	}
}
