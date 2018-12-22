package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class EncoderA extends Encoder{
public static EncoderA enc1;
public static EncoderA enc2;
public static EncoderA getInstance1() {
	if (enc1 == null) {
		enc1 = new EncoderA(1, 0, 205, 6);
	}
	return enc1;
	}
	public static EncoderA getInstance2() {
		if (enc2 == null) {
		enc2 = new EncoderA(3, 2, 205, 6);
		}
		return enc2;
	}
	public EncoderA(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
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
