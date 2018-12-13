package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.PWMSpeedController;

	public class Spark extends PWMSpeedController {
		
		protected Spark(int channel) {
			super(channel);
			// TODO Auto-generated constructor stub
		}

	    public void forward() {
	    	this.set(.25);
	    }

	    public void backwards() {
	    	this.set(-.25);
	    }

	    public void stop() {
	    	this.set(0);
	    }
			
		}
		
