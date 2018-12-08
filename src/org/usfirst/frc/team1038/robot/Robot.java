/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
Spark left = new SparkTest1038(0);
Joystick firstJoystick = new Joystick1038(0); 
Spark right = new SparkTest1038(1);
RobotEncoder Encoder1;
RobotEncoder Encoder2;
//private RobotPneumatics shifter = new RobotPneumatics(2, 3);
//private RobotPneumatics PTOShifter = new RobotPneumatics(0, 1);
Compressor airCompressor = new Compressor(0);
Scheduler schedule = Scheduler.getInstance();
//Joystick1038 secondJoystick;



public void robotInit() {
	Encoder1 = new RobotEncoder(0 , 1 , 207 , 6);
	Encoder2 = new RobotEncoder(2 , 3 , 207 , 6);
	airCompressor.setClosedLoopControl(true);


}

public void teleopPeriodic() {
//		left.set(firstJoystick.getLeftJoystickVertical() * -.5);
//		right.set(firstJoystick.getRightJoystickVertical() * .5);
//
//        left.set(firstJoystick.getRawAxis(1));
        right.set(firstJoystick.getRawAxis(3));
    left.set(-1*firstJoystick.getRawAxis(1));
//    right.set(0.5);
    System.out.println(firstJoystick.getRawAxis(3));
    System.out.println(-1*firstJoystick.getRawAxis(1));
    if(((Joystick1038) firstJoystick).getXButton()) {
		(Pneumatics1038.getFirstInstance()).highGear();
	}
	if(((Joystick1038) firstJoystick).getYButton()) {
		(Pneumatics1038.getFirstInstance()).lowGear();
	}
	if(((Joystick1038) firstJoystick).getAButton()) {
		(Pneumatics1038.getInstance()).PTOOn();
	}
	if(((Joystick1038) firstJoystick).getBButton()) {
		(Pneumatics1038.getInstance()).PTOOff();
	}
}
//        if(firstJoystick.getXButton()) {
//			shifter.highGear();
//		}
//		if(firstJoystick.getYButton()) {
//			shifter.lowGear();
//		}
//	left = new Spark(Wiring.LEFT_MOTOR);
//	right = new Spark(Wiring.RIGHT_MOTOR);
//    firstJoystick = new Joystick(0);




public void autonomousInit(){
//	Encoder1.reset();
//	Encoder2.reset();
}
public void autonomousPeriodic() {
	System.out.println(Encoder1.getCount() + " , " + Encoder2.getCount());
	
	if(Encoder2.getDistance() < 2) {
		right.set(-0.45);
		left.set(-0.45);
	}
	else {
		right.set(0);
		left.set(0);
	}
}


}
