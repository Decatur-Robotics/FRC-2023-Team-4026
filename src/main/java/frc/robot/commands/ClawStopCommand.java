package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class ClawStopCommand extends CommandBase {
    
    ClawIntakeSubsystem clawIntake;

    Value clawMode = Value.kOff;

    public ClawStopCommand(ClawIntakeSubsystem clawIntake) {
        this.clawIntake = clawIntake;
        addRequirements(clawIntake);
    }
    
    public void initialize() {
        clawIntake.clawTime(clawMode, "Button said so");
    }
}
