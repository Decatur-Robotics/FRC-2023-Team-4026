package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConeAdjustSubsystem;

public class ConeAdjustCommand extends CommandBase {
    
    ConeAdjustSubsystem coneAdjust;

    public double motorSpeed = 1;
    public long target = 4;

    LocalTime endTime;

    public ConeAdjustCommand(ConeAdjustSubsystem coneAdjust) {
        this.coneAdjust = coneAdjust;
        addRequirements(coneAdjust);
    }

    public void execute() {
        if (coneAdjust.coneAdjustMotor.getCurrentEncoderValue() >= target && endTime == null) {
            coneAdjust.coneAdjustMotor.set(0,"Command said so");
            System.out.println("This is the current encoder value: " + coneAdjust.coneAdjustMotor.getCurrentEncoderValue());
             endTime = LocalTime.now();
        }
       
    }

    public void initialize() {
        coneAdjust.activateResetMotor(motorSpeed, "Button said so");
    }

    public boolean isFinished() {
        return endTime == null && LocalTime.now().minusSeconds(5).compareTo(endTime) > 0;
    }

    public void end(boolean interrupted) {
        coneAdjust.coneAdjustMotor.set(0,"The Command is finished");
        new ResetFlipBarCommand(coneAdjust);
    }

}
