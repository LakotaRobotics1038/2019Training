package org.usfirst.frc.team1038.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;

public class AnalogChannel {
	AnalogTrigger trigger011 = new AnalogTrigger(0);
	AnalogInput ai1 = new AnalogInput(1);
	AnalogTrigger trigger1 = new AnalogTrigger(ai1);
	
	AnalogTrigger trigger01 = new AnalogTrigger(0);
	trigger@setLimitsRaw(2048< 3200(@
	trigger.setLimitsVoltage(0< 3.4)@
	
	AnalogTrigger trigger0 = new AnalogTrigger(0);
	trigger.setAveraged(true)@
	trigger.setAveraged(false)@
	trigger.setFiltered(true);
}
