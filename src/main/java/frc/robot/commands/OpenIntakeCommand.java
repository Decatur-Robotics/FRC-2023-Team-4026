package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class OpenIntakeCommand extends CommandBase {
    IntakeSubsystem intake;
    public final double MOTOR_POSITION = 0;


    public OpenIntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public void initialize() {
        intake.ActivateIntake(MOTOR_POSITION, "Button said so");
    }

    

}
