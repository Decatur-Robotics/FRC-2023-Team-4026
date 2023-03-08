package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc. robot.subsystems.ClawIntakeSubsystem;

public class ClawGrabberCommand extends CommandBase {
    
    ClawIntakeSubsystem clawIntake;

    Value clawMode;
    LocalTime startTime;
    long timeToWait = 100000000;

    public ClawGrabberCommand(ClawIntakeSubsystem clawIntake, Value clawMode) {
        System.out.println("Constructing ClawGrabberCommand...");
        this.clawIntake = clawIntake;
        this.clawMode = clawMode;
        // addRequirements(clawIntake);
    }

    public void initialize() {        
        System.out.println("Initializing ClawGrabberCommand...");
        clawIntake.setSolenoid(clawMode);
        startTime = LocalTime.now();
    }

    public void execute() {
        System.out.println("Executing ClawGrabberCommand...");
        if (clawIntake.clawGrabber.get() == Value.kOff && startTime == null) {
            clawIntake.setSolenoid(clawMode);
            startTime = LocalTime.now();
        }
    }

    public boolean isFinished() {
        return startTime != null && LocalTime.now().minusNanos(timeToWait).compareTo(startTime) > 0;
    }

    public void end() {
        System.out.println("Ending ClawGrabberCommand...");
        clawIntake.clawGrabber.set(Value.kOff);
    }
}

