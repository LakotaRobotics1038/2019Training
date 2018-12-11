package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;;

public class RobotEncoder extends Encoder {
	public static RobotEncoder firstEncoder;
	public static RobotEncoder secondEncoder;
	
	public RobotEncoder(int channelA, int channelB, int countsPerRevolution, double wheelDiameter) {
		super(channelA, channelB);
		setDistancePerPulse(findDistancePerPulse(countsPerRevolution, wheelDiameter));
	}
	
	public int getCount() {
		return get();
	}
	
	public static double findDistancePerPulse(double countsPerRevolution, double wheelDiameter) {
		return(1 / countsPerRevolution) * (wheelDiameter * Math.PI);
	}
	
}
