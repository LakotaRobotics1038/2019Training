package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class EncoderAZ extends Encoder{
public static EncoderAZ enc1;
public static EncoderAZ enc2;
public static EncoderAZ getInstance1() {
	if (enc1 == null) {
		enc1 = new EncoderAZ(1, 0, 205, 6);
	}
	return enc1;
	}
	public static EncoderAZ getInstance2() {
		if (enc2 == null) {
		enc2 = new EncoderAZ(3, 2, 205, 6);
		}
		return enc2;
	}
	public EncoderAZ(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
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