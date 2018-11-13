package org.usfirst.frc.team1038.robot;

public class MainPID {


	public static void main(String[] args) {
		MiniPID miniPID; 
		
		miniPID = new MiniPID(0.25, 0.01, 0.4);
		miniPID.setOutputLimits(10);

		miniPID.setSetpointRange(40);

		double target=100;
		
		double actual=0;
		double output=0;
		
		miniPID.setSetpoint(0);
		miniPID.setSetpoint(target);
		
		System.err.printf("Target\tActual\tOutput\tError\n");

		for (int i = 0; i < 100; i++){
						
			if (i == 60)
				target = 50;
							
			output = miniPID.getOutput(actual, target);
			actual = actual + output;
			
			System.err.printf("%3.2f\t%3.2f\t%3.2f\t%3.2f\n", target, actual, output, (target-actual));
			
		}		
	}
}