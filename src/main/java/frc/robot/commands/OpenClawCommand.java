package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class OpenClawCommand extends CommandBase {
    
    ClawIntakeSubsystem clawIntake;

    Value clawMode;
    LocalTime endTime;

    public OpenClawCommand(ClawIntakeSubsystem clawIntake, Value clawMode) {
        this.clawIntake = clawIntake;
        addRequirements(clawIntake);
        this.clawMode = clawMode;
    }

    public void initialize() {
        
    }

    public void execute() {

    }
}

