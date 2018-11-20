package org.usfirst.frc.team1038.robot;


public class MyJoystick extends Joystick {

		public Joystick(int port) {
			super(port);
			// TODO Auto-generated constructor stub
		}
		private final int Left_Horizontal = 0;
		private final int Left_Vertical = 1;
		private final int Right_Horizontal = 2;
		private final int Right_Vertical = 3;
		
		public double getLeftJoystickHorizontal() {
			
			return getRawAxis(Left_Horizontal);
		}
			
		public double getLeftJoysickVertical() {
			
			return getRawAxis(Left_Vertical);
		}
		
		public double getRightJoystickHorizontal() {
			
			return getRawAxis(Right_Horizontal);
		}
		
		
		public double getRightJoystickVertical() {
			
			return getRawAxis(Right_Vertical);
		}
	}
}
