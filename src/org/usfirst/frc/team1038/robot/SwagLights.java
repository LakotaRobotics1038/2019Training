package src.org.usfirst.frc.team1038.robot;

import src.org.usfirst.frc.team1038.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SwagLights extends Subsystem {
	public enum WheelWellStates { Disabled, EStop, Red, Blue };
	
	//Wheel Well
	private static final String DISABLED = "D";	
	private static final String E_STOP = "E";
	private static final String RED_ALLIANCE = "R";
	private static final String BLUE_ALLIANCE = "B";
	
	
	
	private String wheelWell = "";
	/*private DriveTrain robotDrive = DriveTrain.getInstance();
	private Elevator elevator = Elevator.getInstance();
	private AcquisitionScoring acqSco = AcquisitionScoring.getInstance();*/
	
	private SerialPort ard = new SerialPort(9600, SerialPort.Port.kMXP);
	private static SwagLights swag;
	
	public static SwagLights getInstance() {
		if (swag == null) {
			System.out.println("Creating new SwagLights");
			swag = new SwagLights();
		}
		return swag;
	}
	
	private SwagLights() {
	}
	
	/**
	 * Changes the state of the wheel well lights
	 * @param state state to change the wheel well lights to
	 */
	public void setWheelWell(WheelWellStates state) {
		switch (state)
		{
		case Blue:
			wheelWell = BLUE_ALLIANCE;
			break;
		case Disabled:
			wheelWell = DISABLED;
			break;
		case EStop:
			wheelWell = E_STOP;
			break;
		case Red:
			wheelWell = RED_ALLIANCE;
			break;
		}
	}
	
	
	
	
	/**
	 * Call this method in robotPeriodic() to write the currently selected state to the arduino
	 */
	public void swagPeriodic() {
		String toWrite = wheelWell + "\r"/* + nameNumber + "\r" + tower + "\r"*/;
		ard.writeString(toWrite);
	}	
	
	/**
	 * Call this method in Teleop and Auton periodic to update the state of the LEDs
	 */
	public void swagEnabledPeriodic() {
			}
	
	/**
	 * Call this method in Teleop and Auton Init to change the leds out of disabled mode
	 */
	public void enable() {
		Robot.disabled = false;
		Robot.eStopped = false;
		
		if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
			setWheelWell(WheelWellStates.Red);
		} else {
			setWheelWell(WheelWellStates.Blue);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
