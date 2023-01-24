package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class OpenIntakeCommand extends CommandBase {
    IntakeSubsystem intake;

    public long targetPosition = 0;


    public OpenIntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public void initialize() {
        intake.setTargetPosition(targetPosition, "Button said so");
    }
}
