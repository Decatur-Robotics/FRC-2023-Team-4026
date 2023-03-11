package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

@Deprecated
public class DriveBackAutoCommand extends CommandBase {

    DriveDistance command;

    DriveTrainSubsystem drivetrain;
    public DriveBackAutoCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    public void initialize() {
        command = new DriveDistance(-50000.0, drivetrain);
        command.schedule();
    }

    public void end() {
        command.cancel();
    }

}
