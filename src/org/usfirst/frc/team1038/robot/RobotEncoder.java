package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class RobotEncoder extends Encoder {
	
	static int countPerRevolution;
	static double wheelsDiameter;
	
	public RobotEncoder(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
		super(channelA, channelB);
		countPerRevolution = countsPerRevolution;
		wheelsDiameter = wheelDiameter;
	}
	
	public int getCount() {
		return get();
	}
	
	public void reset() {
		EncoderJNI.resetEncoder(0);
	}
	
	public static double findDistancePerPulse() {
		return (1 / countPerRevolution) * (wheelsDiameter * Math.PI);
	}
	
	public double findNumberOfCounts(double distancePerCount, double distanceDesired) {
		return (distanceDesired / distancePerCount);
	}
}
