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
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler; // end it
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
	
	
	
	
	public static DriveTrain robotDrive = DriveTrain.getInstance(); // CLING WRAP
	
	
	
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	public static final double WHEEL_DIAM = 6;
	public static final double COUNTS_PER_REV = 207;
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private int currentSolenoid;
	private boolean buttonPressed;
	private ArrayList<Double> errN, errT;
	double kP = 4;
	double kI = 0.002;
	double kD = 0;
	

	private Spark spaRK, spark2;

	private Joystick sticc;
	
	private Encoder encodeman;
	
	/*private DoubleSolenoid ligmoid;
	private DoubleSolenoid ligmoid2;*/
	private ArrayList<DoubleSolenoid> ligmoids;
	
	private DigitalInput finger;
	
	private Servo servo;
	
	private Relay relay;
	
	private CommandGroup group;
	private Scheduler schedule;
	
	private String[][] commands;
	private ArrayList<String[]> recordedInputs = new ArrayList<>();
	private long startTime;
	private int playbackIndex;
	private boolean arrayTouched = false;
	
	private double prevXStick, prevZStick;
	
	
	
	public Compressor comprende = new Compressor(0); // compre d is public
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		spaRK = new Spark(0);
		spark2 = new Spark(1); // make this into an array or something 
		sticc = new Joystick(0);
		encodeman = new Encoder(2,3);
		encodeman.reset();
		/*ligmoid2 = new DoubleSolenoid(2,3);
		ligmoid = new DoubleSolenoid(4,5); // I don't know what port solenoid engages the wheel gear
		ligmoid3 = new DoubleSolenoid(6,7); // I don't know what port solenoid engages the wheel gear
		*/
		ligmoids = new ArrayList<DoubleSolenoid>(0);
		for (int i=0; i<3; i+=2) {
			ligmoids.add(new DoubleSolenoid(i,i+1));
		}
		finger = new DigitalInput(8); // IO PORT 9, not PWM 9
		servo = new Servo(9); // PWM PORT 9, not IO 9
		relay = new Relay(0);
		comprende.setClosedLoopControl(true);
		errN = new ArrayList<>(3);
		errT = new ArrayList<>(3);

		encodeman.setDistancePerPulse(1/207.*6); // (1/207) circumferences per count * 6 inches per circumference

		
		currentSolenoid = 1;
		
		
		schedule = Scheduler.getInstance(); // why does it work???
		schedule.add(new DrivePIDstyle(0, spark2, encodeman));
		

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
//		encodeman.reset();
//		
//		group = new CommandGroup();
//		group.addSequential(new DrivePIDstyle(0,spark2,encodeman));
//		System.out.println("group.addSequential() executed");
//		schedule.add(group);
//		System.out.println("schedule.add() executed");
//		//uncomment this stuff if you want to use PID
		
		startTime = System.currentTimeMillis();
		commands = (String[][])CSV.csv2tab(new File("~/recording.csv"));
		
	}
	
	public void moveDist(double dist, double speed) {
		int curCounts = encodeman.get();
		while (Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get()-curCounts)) < dist) {
			spark2.set(speed);
		}
		spark2.set(0);
		
	
	}
	
	public void teleopInit() {
		encodeman.reset();
		
		startTime = System.currentTimeMillis();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		// Test Recording Things
		
		while ((int)(System.currentTimeMillis()-startTime) < Integer.parseInt(commands[playbackIndex][0])) { 
			playbackIndex++;
		}
		spaRK.set(Double.parseDouble(commands[playbackIndex][2]));
		spark2.set(Double.parseDouble(commands[playbackIndex][3]));
		
		
		
		
		switch (m_autoSelected) {
		case kCustomAuto:
			// Put custom auto code here
			break;
		case kDefaultAuto:
		default:
			// Put default auto code here
			break;
		}
		
		
		//System.out.println(encodeman.getDistance());
		
		
		
		
		/*if (Math.abs(encodeman.get()) < 3000) {
			spade2.set(-(encodeman.get()+4000)/7005.0);
			System.out.println((encodeman.get()+100)/405.0);
		} else {
			spade2.set(0);
			System.out.println("h");
		}*/
		
		/*double doDistance = 120;
		errT.add((double)System.currentTimeMillis());
		errN.add(Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get()))-doDistance);
		
		double PIDt = PID.transform(kP, kI, kD, errN, errT);
		spade2.set(PIDt);
		System.out.println("" + encodeman.get() + ",   " + PIDt);*/ // pid class DEPRECATED so don't use it
		//if (Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get())) >= doDistance)
			//spade2.set(-0);
		//else
			//spade2.set(-1*Math.abs(getDist(COUNTS_PER_REV, WHEEL_DIAM, encodeman.get())-doDistance)/(doDistance*2)-.5);
		
		//if (!group.isRunning())
		
		
//		schedule.run();
//		System.out.println("schedule.run() passed");
//		// uncomment some of this (?) if you want to do PID stuff 
//		
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		// Recorder, please record my inputs!
		spaRK.set(sticc.getX()*1);
		spark2.set(sticc.getZ()*1);
		
		String[] curInput = {""+(int)(System.currentTimeMillis()-startTime), ""+0, ""+(sticc.getX()), ""+(sticc.getZ())};
		
		// @l153, l170
		
		if (sticc.getX() != prevXStick || sticc.getZ() != prevZStick) {
			
			recordedInputs.add(curInput);
			arrayTouched = true;
			if (recordedInputs.size() > 1) {
				int recInpSize = recordedInputs.size();
				recordedInputs.get(recInpSize-1)[1] = ""+(Integer.parseInt(recordedInputs.get(recInpSize)[0]) - Integer.parseInt(recordedInputs.get(recInpSize-1)[0]));
			}
			
			
		}
		
		
		try{File hello = new File("/home/admin/hello.txt");
		hello.createNewFile();
		FileWriter fRead = new FileWriter(hello);
		BufferedWriter fReadBuff = new BufferedWriter(fRead);
		
		fReadBuff.write("Hello World!");
		fReadBuff.close();
		fRead.close();
		System.out.println("look for the file");
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
		Object[][] recordedInputsToArray = new Object[recordedInputs.size()][];
		for (int i = 0; i<recordedInputs.size(); i++) {
			recordedInputsToArray[i] = recordedInputs.get(i);
		}
		System.out.println(Arrays.deepToString(recordedInputsToArray));
		if (!arrayTouched) CSV.tab2csv(recordedInputsToArray, new File("~/recording.csv"));
		System.out.println("tried t o stored recording csv  ONCE");
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		//System.out.println(finger.get());
		//if (!sticc.getRawButton(1)) {
			spaRK.set(sticc.getX()*1);
			spark2.set(sticc.getZ()*1);
		//} else {
		//	spade.set(Math.sin(System.currentTimeMillis()*0.01)*0.5);
		//	spade2.set(Math.cos(System.currentTimeMillis()*0.01)*0.5);
		//}
		//System.out.println(encodeman.get());     // MOVING THE GEARS!!!! MOVING THE GEARS!!!!!
		
		
		////// VVVVVVVVVVVVV COMPRESSOR STUFF (testPeriodic DOES NOT WORK)
		
		
		
		System.out.println("Current Solenoid: " + (int)(currentSolenoid+.5f) + " -- Press X to change");
		System.out.print("                                                      Solenoid States:  ");
		
		for (int sol=0; sol<ligmoids.size(); sol++) {
			System.out.print("" + sol + ":" + ligmoids.get(sol).get() + ",  ");
		}
		System.out.println(".");
		try {
			if ((int)(currentSolenoid+.5f) >= ligmoids.size())
				currentSolenoid = currentSolenoid - 4;
			
			DoubleSolenoid ligmoid = ligmoids.get((int)(currentSolenoid+.5f));
			if (sticc.getRawButton(1)) // X
				if (!buttonPressed) {
					currentSolenoid = (currentSolenoid + 1) % ligmoids.size();
					System.out.println("Changed solenoid to Solenoid " + currentSolenoid);
					buttonPressed = true;
				}
			if (!sticc.getRawButton(1)) // if X is NOT pressed
				if (buttonPressed) {
					buttonPressed = false;
				}
			if (sticc.getRawButton(2)) {// A
				ligmoid.set(DoubleSolenoid.Value.kForward);
				
			}
			if (sticc.getRawButton(3)) // B
				ligmoid.set(DoubleSolenoid.Value.kReverse);
			if (sticc.getRawButton(4)) // Y
				ligmoid.set(DoubleSolenoid.Value.kOff);
		} catch (Exception minecraft) {
			throw minecraft;
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
		
		/*Object[] delays = delaybs.toArray();
		for (int i=0; i<delays.length; i++) {
			relay.set(Relay.Value.kOn);
			try{Thread.sleep(6);}catch(Exception p) {;}
			relay.set(Relay.Value.kForward);
			try{Thread.sleep((long)delays[i]);}catch(Exception p) {;}
		}
		System.exit(9899899889);*/
        // maybe it's just because auton periodic doesn't work
		
//		if (schedule != null) {
//			schedule.run();
//			
//		}
//		//uncomment all this if you want to do PID stuff

	}
	

	/**
	 * This function is called periodically during test mode.
	 */

	
	
	@Override
	public void testPeriodic() {
		
		
		
		
		
		
		          System.out.println("why are you in test lol");
		
			
		
		
	}


	
	//return same unit as you put wheel diam lmao xdddddddd
	public double getDist(double countsPerRev, double wheelDiam, int totalCounts) {
		return ((double)totalCounts/countsPerRev*(wheelDiam*Math.PI));
	}
}
