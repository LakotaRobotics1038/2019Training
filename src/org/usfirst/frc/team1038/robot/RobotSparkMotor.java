package org.usfirst.frc.team1038.robot;
import edu.wpi.first.wpilibj.Spark;

public class RobotSparkMotor extends Spark {
	
	public static RobotSparkMotor firstMotor;
	public static RobotSparkMotor secondMotor;
	
	public static RobotSparkMotor getInstance() {
		if (firstMotor == null) {
			firstMotor = new RobotSparkMotor(0);
		}
		return firstMotor;
	}
	
	public static RobotSparkMotor getFirstInstance() {
		if (secondMotor == null) {
			secondMotor = new RobotSparkMotor(1);
		}
		return secondMotor;
	}

	public RobotSparkMotor(final int channel) {
		super(channel);
	}
	
}
