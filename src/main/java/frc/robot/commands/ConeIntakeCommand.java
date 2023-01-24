package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ConeIntakeCommand extends CommandBase {
    IntakeSubsystem intake;

    public long targetPosition = 4;



    public ConeIntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public void initialize() {
        intake.setTargetPosition(targetPosition, "Button said so");
    }

}
