package org.usfirst.frc.team1038.robot;

import java.util.ArrayList;

public class PID {

	
	public static double transform(double kP, double kI, double kD, ArrayList<Double> errN, ArrayList<Double> errT) { //errN: errors, errT: times for each error
		int errLen = errN.size();
		if (errLen != errT.size() || errLen <3) {
			return 0.5; // no
		}
		
		
		//sumErr
		double sumErr = 0;
		for (int i=1; i<errLen; i++) {
			sumErr += errN.get(i)*(errT.get(i)-errT.get(i-1));
		}
		
		//dErr
		double dErr = (errN.get(errLen-1)-errN.get(errLen-2))/(errT.get(errLen-1)-errT.get(errLen-2));
		
		return kP*errN.get(errLen-1) + kI*sumErr + kD*dErr; 
	}	
	
}
