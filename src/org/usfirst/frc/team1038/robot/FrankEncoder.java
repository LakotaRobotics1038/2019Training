package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class FrankEncoder extends Encoder{
	
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
