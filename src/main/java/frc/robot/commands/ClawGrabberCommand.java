package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc. robot.subsystems.ClawIntakeSubsystem;

public class ClawGrabberCommand extends CommandBase {
    
    ClawIntakeSubsystem clawIntake;

    Value clawMode;
    LocalTime endTime;
    long timeToWait = 3;

    public ClawGrabberCommand(ClawIntakeSubsystem clawIntake, Value clawMode) {
        this.clawIntake = clawIntake;
        addRequirements(clawIntake);
        this.clawMode = clawMode;
    }

    public void execute() {
        if (clawIntake.clawGrabber.get() == Value.kOff && endTime == null) {
            clawIntake.clawGrabber.set(clawMode);
            endTime = LocalTime.now();
        }
    }

    public boolean isFinished() {
        return endTime == null && LocalTime.now().minusSeconds(timeToWait).compareTo(endTime) > 0;
    }

    public void end() {
        clawIntake.clawGrabber.set(Value.kOff);
    }
}
