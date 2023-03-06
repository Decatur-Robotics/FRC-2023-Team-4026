package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class IntakeMotorCommand extends CommandBase {
    
    public ClawIntakeSubsystem intake;

    double motorSpeed = Constants.intakeMotorSpeed;

    public IntakeMotorCommand(ClawIntakeSubsystem intake) {
        this.intake = intake;
    }

    public void execute() {
        intake.intakeMotor.set(motorSpeed, "running intake motor");
    }

    public void end() {
        intake.intakeMotor.set(Constants.stopSpeed, "stopping intake motor");
    }

}
