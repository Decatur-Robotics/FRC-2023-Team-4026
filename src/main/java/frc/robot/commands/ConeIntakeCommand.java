package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class ConeIntakeCommand extends CommandBase {
    ClawIntakeSubsystem intake;

    public long targetPosition = 4;



    public ConeIntakeCommand(ClawIntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    //public void initialize() {
        //intake.setTargetPosition(targetPosition, "Button said so");
    //}

}
