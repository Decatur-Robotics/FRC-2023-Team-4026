package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class SpeedModeCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;
    public double speedMod;


    public SpeedModeCommand(DriveTrainSubsystem drivetrain, double newSpeedMod) {
        this.drivetrain = drivetrain;
        
        speedMod = newSpeedMod;
    }

    public void initialize() {
        drivetrain.setSpeedMod(speedMod);
    }

    public void end() {
        drivetrain.setSpeedMod(Constants.NORMAL_SPEED);
    }
}
