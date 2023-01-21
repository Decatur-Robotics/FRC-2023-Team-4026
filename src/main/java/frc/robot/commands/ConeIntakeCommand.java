package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ConeIntakeCommand extends CommandBase {
    IntakeSubsystem intake;

    public long target = 3;
    public double motorSpeed = 1;


    public ConeIntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public void execute() {
        if (intake.intakeMotor.getCurrentEncoderValue() >= target) {
            intake.intakeMotor.set(0,"Command said so");
            System.out.println("This is the current encoder value: " + intake.intakeMotor.getCurrentEncoderValue());
        }
    }

    public void initialize() {
        intake.ActivateIntake(motorSpeed, "Button said so");
    }

    public boolean isFinished() {
        return intake.intakeMotor.getCurrentEncoderValue() >= target;
    }

    public void end(boolean interrupted) {
        intake.intakeMotor.set(0,"The Command is finished");
    }    

}
