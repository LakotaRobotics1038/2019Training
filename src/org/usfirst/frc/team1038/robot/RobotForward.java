package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RobotForward extends RobotAuton {
	
	public RobotForward() {
		super();
	}
	
	public CommandGroup select() {
		group.addSequential(new RobotDriveStraight(2));
		return group;
	}
}
