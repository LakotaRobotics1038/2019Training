package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.PWMSpeedController;

	public class Spark2 extends PWMSpeedController {

		protected Spark2(int channel) {
			super(channel);
			// TODO Auto-generated constructor stub
		}

		public void motorsgo() 
		{
		this.set(.5);
		}
		
		public void motors-stop()
		{
		this.set(0);	
		}
		 
		public void motorsback()
		{
		this.set(-.5)	
		}
		
		}
	}
