package org.usfirst.frc.team1038.robot;

import java.util.concurrent.TimeUnit;

public class PIDController {

    private double setPoint;
    private double pGain;
    private double iGain;
    private double dGain;

    private long lastTime;
    private double lastError = 0;
    private double integral = 0;

    public PIDController(double set, double p, double i, double d) {

        reset(set);

        pGain = p;
        iGain = i;
        dGain = d;
    }

    public Double update(long currTime, double currValue) {

        if (lastTime == 0) {

            lastTime = currTime;
            lastError = setPoint - currValue;

            return null;
        }

        double dt = (double)(currTime - lastTime) / TimeUnit.SECONDS.toNanos(1);

        if (dt == 0) {
            return null;
        }

        double error = setPoint - currValue;
        double deriv = (error - lastError) / dt;

        integral += error * dt;
        lastTime = currTime;
        lastError = error;

        return (pGain * error) + (iGain * integral) + (dGain * deriv);
    }
    
    public void setSetpoint(double set) {
        reset(set);
    }

    private void reset(double set) {

        setPoint = set;

        lastTime = 0;
        lastError = 0;
        integral = 0;
    }
}