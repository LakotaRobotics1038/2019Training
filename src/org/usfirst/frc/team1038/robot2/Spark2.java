package org.usfirst.frc.team1038.robot2;

import edu.wpi.first.wpilibj.PWMSpeedController;

	public class Spark2 extends PWMSpeedController {

		protected Spark2(int channel) {
			super(channel);
			// TODO Auto-generated constructor stub
		}

		public void motorsgo(double speed) 
		{
		this.set(speed);
		}
		
		public void motorsstop()
		{
		this.set(0);	
		}
		 
		public void motorsback()
		{
		this.set(-.5);	
		}
		
		}
	
