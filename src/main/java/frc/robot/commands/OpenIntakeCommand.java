package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class OpenIntakeCommand extends CommandBase {
    ClawIntakeSubsystem intake;

    public long targetPosition = 0;


    public OpenIntakeCommand(ClawIntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    //public void initialize() {
        //intake.setTargetPosition(targetPosition, "Button said so");
    //}
}
