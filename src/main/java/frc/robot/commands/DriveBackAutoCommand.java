package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveBackAutoCommand extends CommandBase {

    DriveDistance command;

    public DriveBackAutoCommand() {

    }

    public void initialize() {
        command = new DriveDistance(-50000.0, RobotContainer.drivetrain);
        command.schedule();
    }

    public void end() {
        command.cancel();
    }

}
