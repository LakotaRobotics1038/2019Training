/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
SparkTest1038 sparkTest;
Joystick1038 firstJoystick;
Spark secondMotor;
Joystick1038 secondJoystick;


public void robotInit() {
	sparkTest = new SparkTest1038(0);
	firstJoystick = new Joystick1038(0);
	secondMotor = new SparkTest1038(1);
}

public void teleopPeridoic() {
	if(firstJoystick.getBButton()) {
		sparkTest.set(0.7);
	}
	if(secondJoystick.getBButton()) {
		sparkTest.set(0.7);
	}

}


}
