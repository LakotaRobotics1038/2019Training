package org.usfirst.frc.team1038.robot;
import edu.wpi.first.wpilibj.Spark;

public class FrankMotor extends Spark {
	
	public static FrankMotor firstMotor;
	public static FrankMotor secondMotor;
	
	public static FrankMotor getInstance() {
		if (firstMotor == null) {
			firstMotor = new FrankMotor(0);
		}
		return firstMotor;
	}
	
	public static FrankMotor getFirstInstance() {
		if (secondMotor == null) {
			secondMotor = new FrankMotor(1);
			secondMotor.setInverted(true);
		}
		return secondMotor;
	}

	public FrankMotor(final int channel) {
		super(channel);
	}
	
}