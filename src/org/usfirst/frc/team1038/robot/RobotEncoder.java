package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class RobotEncoder extends Encoder {
	
	public static RobotEncoder firstEncoder;
	public static RobotEncoder secondEncoder;
	
	public static RobotEncoder getInstance() {
		if (firstEncoder == null) {
			firstEncoder = new RobotEncoder(0, 1, 205, 6);
		}
		return firstEncoder;
	}
	
	public static RobotEncoder getFirstInstance() {
		if (secondEncoder == null) {
			secondEncoder = new RobotEncoder(2, 3, 205, 6);
		}
		return secondEncoder;
	}
	
	public RobotEncoder(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
		super(channelA, channelB);
		setDistancePerPulse(findDistancePerPulse(countsPerRevolution, wheelDiameter));
	}
	
	public int getCount() {
		return get();
	}
	
	public void reset() {
		EncoderJNI.resetEncoder(0);
	}
	
	public static double findDistancePerPulse(double countsPerRevolution, double wheelDiameter) {
		return (1 / countsPerRevolution) * (wheelDiameter * Math.PI);
	}
	
}
