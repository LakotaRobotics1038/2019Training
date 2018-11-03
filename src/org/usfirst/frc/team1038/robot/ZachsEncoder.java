package org.usfirst.frc.team1038.robot;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;


public class ZachsEncoder extends Encoder {
	static int countPerRevolution;
	static double wheelDiameter;
	
	public ZachsEncoder(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
		super(channelA, channelB);
		countPerRevolution = countsPerRevolution;
	}
	
	public int getCount() {
		return get(	);
}
}
