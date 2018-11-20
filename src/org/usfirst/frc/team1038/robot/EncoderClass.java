package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class EncoderClass extends Encoder { public EncoderClass(final int channelA, final int channelB) {
super(channelA, channelB);}
public int getCount() {
	return get();
}

public void reset() {
	EncoderJNI.resetEncoder(0);
	
}

}