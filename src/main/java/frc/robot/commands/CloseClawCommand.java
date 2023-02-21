package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class CloseClawCommand extends CommandBase {
    
    ClawIntakeSubsystem clawIntake;

    Value clawMode = Value.kReverse;

    public CloseClawCommand(ClawIntakeSubsystem clawIntake) {
        this.clawIntake = clawIntake;
        addRequirements(clawIntake);
    }
    
    public void initialize() {
        clawIntake.clawTime(clawMode, "Button said so");
    }
}
