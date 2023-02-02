package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelIntakeSubsystem;

public class WheelIntakeCommand extends CommandBase {
    WheelIntakeSubsystem wheelIntake;

    public WheelIntakeCommand(WheelIntakeSubsystem wheelIntake) {
        this.wheelIntake = wheelIntake;
        addRequirements(wheelIntake);
    }

    public void initialize() {
        wheelIntake.RunIntake("Button said so");
    }
}
