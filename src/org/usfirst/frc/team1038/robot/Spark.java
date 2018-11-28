package org.usfirst.frc.team1038.robot;

public class Spark {
	package org.usfirst.frc.team1038.robot;

	import edu.wpi.first.wpilibj.PWMSpeedController;

		public class Spark extends PWMSpeedController {

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
}
