/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
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
	private boolean compressorOn;

	private Spark spade, spade2;

	private Joystick sticc;
	
	private Encoder encodeman;
	
	private DoubleSolenoid ligmoid;

	private DigitalInput finger;
	
	private Servo servo; //out of bad names
	
	private Relay relay;
	
	private Compressor comprende;
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
		ligmoid = new DoubleSolenoid(2,3);
		finger = new DigitalInput(8); // IO PORT 9, not PWM 9
		servo = new Servo(9); // PWM PORT 9, not IO 9
		relay = new Relay(0); //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		comprende = new Compressor(0); // compre d lkjdljkjdljksjldkjlksjdlkj
		compressorOn = false;
		

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
		System.out.println(finger.get());
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
		System.out.println(comprende.enabled());
		try {
			if (sticc.getRawButton(1)) // X
				ligmoid.set(DoubleSolenoid.Value.kOff);
			if (sticc.getRawButton(2)) {// A
				if (compressorOn)
					comprende.start();
				else
					comprende.stop();
				compressorOn = !compressorOn;
				while (sticc.getRawButton(1)) {
					; // let go of the button you gamer
					//also this code might not work
				}
			}
			if (sticc.getRawButton(3)) // B
				ligmoid.set(DoubleSolenoid.Value.kForward);
			if (sticc.getRawButton(4)) // Y
				ligmoid.set(DoubleSolenoid.Value.kReverse);
			if (sticc.getRawButton(5)) // LB
				
					{ligmoid.set(DoubleSolenoid.Value.kForward);
					try{Thread.sleep(30);}catch(Exception p) {;}
					ligmoid.set(DoubleSolenoid.Value.kReverse);
					try{Thread.sleep(30);}catch(Exception p) {;}
					System.out.println("RELEASING THE KRAKEN");} // compressor people be like BBBBRRRRRRRRRRRRRRRRRRRR
				
		} catch (Exception minecraft) {
			;;;;;;;;;;
		}
		
		/*relay.set(Relay.Value.kOn);
		try{Thread.sleep(3);}catch(Exception p) {;}
		relay.set(Relay.Value.kForward);
		try{Thread.sleep(3);}catch(Exception p) {;}*/ // big loud clicker
		
		// set motor by limit switch
		/*spade2.set(finger.get() ? -0.4 : 0.4);
		System.out.println(finger.get());*/
		
		// turn on light (press between X and B)
		/*servo.set((sticc.getX()/2)+0.5);
		System.out.println((sticc.getX()/2)+0.5);
		if (sticc.getRawButton(0)) // X
			relay.set(Relay.Value.kOff);
		if (sticc.getRawButton(1)) // A
			relay.set(Relay.Value.kOn);
		if (sticc.getRawButton(2)) // B
			relay.set(Relay.Value.kForward);
		if (sticc.getRawButton(3)) // Y
			relay.set(Relay.Value.kReverse);*/
		
		
		//long[] delays = {400,400,400,66*4,33*4,400,66*4,33*4,400}; // star wars thing
		/*ArrayList<Long> delaybs = new ArrayList<Long>(300);
		for (int i=200;i>0;i--) {
			delaybs.add((long)i);
		} */
		// brapp
		/*Object[] delays = delaybs.toArray();
		for (int i=0; i<delays.length; i++) {
			relay.set(Relay.Value.kOn);
			try{Thread.sleep(6);}catch(Exception p) {;}
			relay.set(Relay.Value.kForward);
			try{Thread.sleep((long)delays[i]);}catch(Exception p) {;}
		}
		System.exit(9899899889);*/
		
		
		
		
		
		
		
		
			
		
		
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
