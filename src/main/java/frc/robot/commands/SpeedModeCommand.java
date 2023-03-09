package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class SpeedModeCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;


    public SpeedModeCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void initialize() {
        drivetrain.setSpeedMod(Constants.FAST_SPEED);
    }

    public void end() {
        drivetrain.setSpeedMod(Constants.NORMAL_SPEED);
    }
}
