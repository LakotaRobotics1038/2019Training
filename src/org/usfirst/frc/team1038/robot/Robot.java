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
	
Spark left = new SparkTest1038(0);
Joystick1038 firstJoystick = new Joystick1038(0); 
Spark right = new SparkTest1038(1);
//Joystick1038 secondJoystick;



public void robotInit() {

}

public void teleopPeridoic() {
		left.set(firstJoystick.getLeftJoystickVertical() * .5);
		right.set(firstJoystick.getRightJoystickVertical() * .5);

        left.set(firstJoystick.getRawAxis(1));
        right.set(firstJoystick.getRawAxis(3));



}
public void autonomousPeriodic() {
	System.out.println(Encoder1.getCount() + " , " + Encoder2.getCount());
	if(Encoder1.getDistance() < 12) {
		left.set(1);
	}
	else {
		left.set(0);
	}
	if(Encoder2.getDistance() < 12) {
		right.set(1);
	}
	else {
		right.set(0);
	}
}


}
