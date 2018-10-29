package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SensorBase;

public class RobotCompressor extends Compressor {
	
	public RobotCompressor(int module) {
		super(SensorBase.getDefaultSolenoidModule());
	}

}
