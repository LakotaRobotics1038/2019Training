package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class PIDControl{
Joystick turrentStick();
AnalogChannel turrentPot();
PIDController turretControl (0.1, 0.001, 0.0, &turretPot, &turretMotor);
turretControl.Enable();
while(IsOperator())
{
	turretControl.SetSetpoint((turretStick.GetX()+1.0)*2.5);
	Wait(.02);
}
}