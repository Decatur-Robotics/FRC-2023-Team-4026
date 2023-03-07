package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClawIntakeSubsystem;

public class IntakeMotorCommand extends CommandBase {
    
    public ClawIntakeSubsystem intake;

    double motorSpeed = Constants.intakeMotorSpeed;

    BooleanSupplier input;

    public IntakeMotorCommand(ClawIntakeSubsystem intake, BooleanSupplier in) {
        this.intake = intake;
        input = in;
    }

    public void execute() {
        intake.intakeMotor.set(input.getAsBoolean() ? motorSpeed : 0, "running intake motor");
    }

    public void end() {
        intake.intakeMotor.set(Constants.stopSpeed, "stopping intake motor");
    }

}
