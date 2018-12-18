package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
	public enum driveModes { tankDrive, singleArcadeDrive, dualArcadeDrive };
	public driveModes currentDriveMode = driveModes.dualArcadeDrive;
	private driveModes prevDriveMode = currentDriveMode;
	private final int LEFT_ENCODER_CHANNEL_A = 0;
	private final int LEFT_ENCODER_CHANNEL_B = 1;
	private final int RIGHT_ENCODER_CHANNEL_A = 2;
	private final int RIGHT_ENCODER_CHANNEL_B = 3;
	public final int ENCODER_COUNTS_PER_REV = 205; //910 Talon 220 proto bot
	public final double WHEEL_DIAMETER = 6;
	//private static final int TIMEOUT_MS = 50;
	//private double distPerPulse;
	private final static int LEFT_DRIVE_PORT = 0;
	private final static int RIGHT_DRIVE_PORT = 1;
	private static Spark leftDrive = new Spark(LEFT_DRIVE_PORT);
	private static Spark rightDrive = new Spark(RIGHT_DRIVE_PORT);
	public  Spark rightDrive2 = new Spark(2); // 4 for other robot
//	private SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2);
//	private SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2);
//	private DoubleSolenoid shifter = new DoubleSolenoid(2, 3);
//	private DoubleSolenoid PTO = new DoubleSolenoid(5, 4);
	private EncoderSensor leftDriveEncoder = new EncoderSensor(LEFT_ENCODER_CHANNEL_A, LEFT_ENCODER_CHANNEL_B, false, ENCODER_COUNTS_PER_REV, WHEEL_DIAMETER);
	private EncoderSensor rightDriveEncoder = new EncoderSensor(RIGHT_ENCODER_CHANNEL_A, RIGHT_ENCODER_CHANNEL_B, false, ENCODER_COUNTS_PER_REV, WHEEL_DIAMETER);
	private boolean isHighGear = false;
	private boolean PTOisEngaged = false;
	private DifferentialDrive differentialDrive;
	private static DriveTrain driveTrain;

	public static DriveTrain getInstance() {
		if (driveTrain == null) {
			System.out.println("Creating a new DriveTrain");
			driveTrain = new DriveTrain();
		}
		return driveTrain;
	}
	
	private DriveTrain() {
		//leftDrive1.setInverted(true);
		//rightDrive1.setInverted(true);
		//distPerPulse = Encoder1038.findDistancePerPulse(ENCODER_COUNTS_PER_REV, WHEEL_DIAMETER);
		rightDrive2.setInverted(true);
		differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
	}
	
	/**
	 * Get the encoder counts driven by the left of the robot
	 * @return the encoder counts driven by the left of the robot
	 */
	public int getLeftDriveEncoderCount() {
		return leftDriveEncoder.getCount();
//		return leftDrive1.getSensorCollection().getQuadraturePosition();
	}
	
	/**
	 * Get the encoder counts driven by the right of the robot
	 * @return the encoder counts driven by the right of the robot
	 */
	public int getRightDriveEncoderCount() {
		return rightDriveEncoder.getCount();
//		return rightDrive1.getSensorCollection().getQuadraturePosition() * -1;
	}
	 
	/**
	 * Get the distance driven by the left of the robot in inches
	 * @return distance driven by the left of the robot in inches
	 */
	public double getLeftDriveEncoderDistance() {
		return leftDriveEncoder.getDistance();
//		return leftDrive1.getSensorCollection().getQuadraturePosition() * distPerPulse;
	}
	
	/**
	 * Get the distance driven by the right of the robot in inches
	 * @return distance driven by the right of the robot in inches
	 */
	public double getRightDriveEncoderDistance() {
		return rightDriveEncoder.getDistance();
//		return rightDrive1.getSensorCollection().getQuadraturePosition() * -distPerPulse;
	}
		
	/**
	 * reset the drive encoders
	 */
	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
//		leftDrive1.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
//		rightDrive1.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}
	
	/**
	 * Set the drive train to brake mode
	 */
//	public void setBrakeMode() {
//		leftDrive1.setNeutralMode(NeutralMode.Brake);
//		leftDrive2.setNeutralMode(NeutralMode.Brake);
//		rightDrive1.setNeutralMode(NeutralMode.Brake);
//		rightDrive2.setNeutralMode(NeutralMode.Brake);
//		System.out.println("Brake Mode");
//	}
//	
//	/**
//	 * Set the drive train to coast mode
//	 */
//	public void setCoastMode() {
//		leftDrive1.setNeutralMode(NeutralMode.Coast);
//		leftDrive2.setNeutralMode(NeutralMode.Coast);
//		rightDrive1.setNeutralMode(NeutralMode.Coast);
//		rightDrive2.setNeutralMode(NeutralMode.Coast);
//		System.out.println("Coast Mode");
//	}
	
	/**
	 * Toggles drive mode. Order from tank, to dual aracde, to single arcade, to tank
	 */
	public void toggleDriveMode()
	{
		if (currentDriveMode == driveModes.tankDrive && prevDriveMode != driveModes.tankDrive) {
			currentDriveMode = driveModes.dualArcadeDrive;
			prevDriveMode = currentDriveMode;
		}	
		else if (currentDriveMode == driveModes.dualArcadeDrive && prevDriveMode != driveModes.dualArcadeDrive) {
			currentDriveMode = driveModes.singleArcadeDrive;
			prevDriveMode = currentDriveMode;
		}			
		else if (currentDriveMode == driveModes.singleArcadeDrive && prevDriveMode != driveModes.singleArcadeDrive) {
			currentDriveMode = driveModes.tankDrive;	
			prevDriveMode = currentDriveMode;
		}
	}
	
	/**
	 * Drive robot using tank drive (left stick controls left side, right stick controls right side)
	 * @param inputL Left stick input (range -1 to 1)
	 * @param inputR Right stick input (range -1 to 1)
	 */
	public void tankDrive(double inputL, double inputR) {
		if (PTOisEngaged) {
			differentialDrive.tankDrive(-Math.abs(inputL), -Math.abs(inputL), true);
		}
		else {
			differentialDrive.tankDrive(inputL, inputL, true); //second is inputR
			rightDrive2.set(inputL); //inputR
		}
	}
	
	/**
	 * Drive robot using single stick 
	 * @param speed Speed of robot (range -1 to 1)
	 * @param curve Wanted turn value of robot
	 */
	public void singleArcadeDrive(double speed, double curve) {
		if (PTOisEngaged)
			differentialDrive.arcadeDrive(-Math.abs(speed), 0, true);
		else
			differentialDrive.arcadeDrive(speed, curve, true);
	}
	
	/**
	 * Drive robot using two sticks (one controlling forward and backward, the other controlling left and right) 
	 * @param inputFB Forward/Backward value (range -1 to 1)
	 * @param inputLR Left/Right value (range -1 to 1)
	 */
	public void dualArcadeDrive(double yaxis, double xaxis) {
		if (PTOisEngaged)
			differentialDrive.arcadeDrive(-Math.abs(yaxis), 0, true);
		else
			differentialDrive.arcadeDrive(yaxis, xaxis, true);
	}
	
	/**
	 * Drive robot based on a speed and rotation given
	 * @param moveVal Speed (range -1 to 1)
	 * @param rotateVal Rotation (range -1 to 1)
	 */
	public void drive(double moveVal, double rotateVal) {
		differentialDrive.curvatureDrive(moveVal, rotateVal, false);
	}
	
	/**
	 * Toggle the PTO between on and off
	 */
//	public void togglePTO() {
//		if(PTOisEngaged) {
//			PTOoff();
//		}else {
//			PTOon();
//		}
//	}
//	
//	/**
//	 * Change PTO to on
//	 */
//	public void PTOon() {
//		PTOisEngaged = true;
//		PTO.set(DoubleSolenoid.Value.kReverse);
//	}
//	
//	/**
//	 * Change PTO to off
//	 */
//	public void PTOoff() {
//		PTOisEngaged = false;
//		PTO.set(DoubleSolenoid.Value.kForward);
//	}
//	
//	/**
//	 * Toggle the Gear between high and low
//	 */
//	public void toggleGear() {
//		if(isHighGear) {
//			lowGear();
//		}else {
//			highGear();
//		}
//	}
//	
//	/**
//	 * Change gear to high
//	 */
//	public void highGear() {
//		isHighGear = true;
//		shifter.set(DoubleSolenoid.Value.kForward);
//	}
//	
//	/**
//	 * Change gear to low
//	 */
//	public void lowGear() {
//		isHighGear = false;
//		shifter.set(DoubleSolenoid.Value.kReverse);
//	}
	
	/**
	 * Returns if the gear is set to high
	 * @return is the robot in high gear
	 */
	public boolean isHighGear() {
		return isHighGear;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Returns if the PTO is engaged
	 * @return is PTO engaged
	 */
}
