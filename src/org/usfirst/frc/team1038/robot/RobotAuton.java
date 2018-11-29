package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class RobotAuton {
	
	CommandGroup group;
	
	public RobotAuton() {
		group = new CommandGroup();
	}
	
	public abstract CommandGroup select();
}
