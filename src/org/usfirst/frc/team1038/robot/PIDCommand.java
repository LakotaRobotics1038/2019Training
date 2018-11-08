package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class PIDCommand{
	    private static final PIDController PIDController = null;  
	    private static final double Setpoint = 0;
		private static final double Position = 0;
		private static final double PIDInput = 0;

		double p;
        double i;
        double d;
        double period;

        protected PIDController getPIDController() {
			return PIDController;
}
        public void setSetpointRelative(double deltaSetpoint) {
}
protected void setSetpoint(double setpoint) {
}
protected double getSetpoint() {
	return Setpoint;
}

protected double getPosition() {
	return Position;
}
protected void setInputRange(double minimumInput,
        double maximumInput) {
}
protected double returnPIDInput() {
	return PIDInput;
}

protected void usePIDOutput(double output) {
}
public void initSendable(SendableBuilder builder) {
}
}