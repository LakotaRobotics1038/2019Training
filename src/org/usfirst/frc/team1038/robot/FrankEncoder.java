package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class FrankEncoder extends Encoder{
	
	public static FrankEncoder firstEncoder;
	public static FrankEncoder secondEncoder;
	
	public static FrankEncoder getInstance() {
		if (firstEncoder == null) {
			firstEncoder = new FrankEncoder(0, 1, 205, 6);
		}
		return firstEncoder;
	}
	
	public static FrankEncoder getFirstInstance() {
		if (secondEncoder == null) {
			secondEncoder = new FrankEncoder(2, 3, 205, 6);
		}
		return secondEncoder;
	}
	
	public FrankEncoder(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
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
