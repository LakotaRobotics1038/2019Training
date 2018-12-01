package org.usfirst.frc.team1038.robot;
import edu.wpi.first.wpilibj.Spark;

public class RobotSparkMotor extends Spark {
	
	public static RobotSparkMotor firstMotor;
	public static RobotSparkMotor secondMotor;
	public static RobotSparkMotor thirdMotor;
	
	public static RobotSparkMotor getInstance() {
		if (firstMotor == null) {
			firstMotor = new RobotSparkMotor(0);
		}
		return firstMotor;
	}
	
	public static RobotSparkMotor getFirstInstance() {
		if (secondMotor == null) {
			secondMotor = new RobotSparkMotor(3);
			secondMotor.setInverted(true);
		}
		return secondMotor;
	}
	
	public static RobotSparkMotor getSecondInstance() {
		if (thirdMotor == null) {
			thirdMotor = new RobotSparkMotor(2);
			thirdMotor.setInverted(true);
		}
		return thirdMotor;
	}

	public RobotSparkMotor(final int channel) {
		super(channel);
	}
	
}
