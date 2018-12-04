package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

public class EncoderAaronZ extends Encoder{
	public EncoderAaronZ(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
		super(channelA, channelB, reverseDirection, encodingType);
		// TODO Auto-generated constructor stub
	}
	public static EncoderAaronZ enc;
	
	public static EncoderAaronZ getInstance() {
		if(enc == null) {
			enc = new EncoderAaronZ(0, 1, false, Encoder.EncodingType.k4X);
		}
		return enc;
	}
}


 
   
  
