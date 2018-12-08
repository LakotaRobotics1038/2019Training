/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
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
private String m_autoSelected;
private SendableChooser<String> m_chooser = new SendableChooser<>();
Spark motor1 = new Spark(0);
Spark motor2 = new Spark(1);
Spark motor3 = new Spark(3);
Spark motor4 = new Spark(4);
JoystickAZ LJoystick = new JoystickAZ(1);
EncoderAZ enc1 = EncoderAZ.getInstance1();
EncoderAZ enc2 = EncoderAZ.getInstance2();
Compressor compressor1 = new Compressor(0);
/**
* This function is run when the robot is first started up and should be
* used for any initialization code.
*/
@Override
public void robotInit() {
m_chooser.addDefault("Default Auto", kDefaultAuto);
m_chooser.addObject("My Auto", kCustomAuto);
SmartDashboard.putData("Auto choices", m_chooser);
compressor1.setClosedLoopControl(true);
}

/**
* This autonomous (along with the chooser code above) shows how to select
* between different autonomous modes using the dashboard. The sendable
* chooser code works with the Java SmartDashboard. If you prefer the
* LabVIEW Dashboard, remove all of the chooser code and uncomment the
* getString line to get the auto name from the text box below the Gyro
*
*
You can add additional auto modes by adding additional comparisons to

* the switch structure below with additional strings. If using the
* SendableChooser make sure to add them to the chooser code above as well.
*/
@Override
public void autonomousInit() {
	
m_autoSelected = m_chooser.getSelected();
// autoSelected = SmartDashboard.getString("Auto Selector",
// defaultAuto);
System.out.println("Auto selected: " + m_autoSelected);

}

/**
* This function is called periodically during autonomous.
*/
@Override
public void autonomousPeriodic() {
System.out.println(enc1.getCount() + " , " + enc2.getCount());
System.out.println(enc1.getDistance() + " , " + enc2.getDistance());
motor1.set(.25);//tested moves kinda fast at.022987 
motor2.set(.25);//tested, not movement for port 2 and test port one and no movement
//motor3.set(.25);// by itself it kind of moves but it does MOVE!!!
//motor4.set(.25);///kinda moved by itself now testing motor 3 and 4 together

}




/**
* This function is called periodically during operator control.
*/
@Override
public void teleopPeriodic() {
motor1.set(LJoystick.getLeftJoystickVertical() * .5);
motor2.set(LJoystick.getLeftJoystickVertical() * .5);
}


/**
* This function is called periodically during test mode.
*/
@Override
public void testPeriodic() {
}
}