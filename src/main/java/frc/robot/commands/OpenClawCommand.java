package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class OpenClawCommand extends CommandBase {
    
    ClawIntakeSubsystem clawIntake;

    public long targetPressure = 500;

    public OpenClawCommand(ClawIntakeSubsystem clawIntake) {
        this.clawIntake = clawIntake;
        addRequirements(clawIntake);
    }

    public void initialize() {
        clawIntake.clawTime(true, "Button said so");
    }
}
