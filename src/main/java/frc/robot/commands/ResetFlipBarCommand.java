package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConeAdjustSubsystem;

public class ResetFlipBarCommand extends CommandBase {
    ConeAdjustSubsystem coneAdjust;

    public double motorSpeed = -1;
    public long target = 1;

    public ResetFlipBarCommand(ConeAdjustSubsystem coneAdjust) {
        this.coneAdjust = coneAdjust;
        addRequirements(coneAdjust);
    }

    public void execute() {
        if (coneAdjust.coneAdjustMotor.getCurrentEncoderValue() <= target) {
            coneAdjust.coneAdjustMotor.set(0,"Command said so");
            System.out.println("This is the current encoder value: " + coneAdjust.coneAdjustMotor.getCurrentEncoderValue());
        }
    }

    public void initialize() {
        coneAdjust.activateResetMotor(motorSpeed, "Button said so");
    }

    public boolean isFinished() {
        return coneAdjust.coneAdjustMotor.getCurrentEncoderValue() <= target;
    }

    public void end(boolean interrupted) {
        coneAdjust.coneAdjustMotor.set(0,"The Command is finished");
    }
}
