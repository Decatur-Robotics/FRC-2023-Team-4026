package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class SpeedModeCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;
    public double speedMod;


    public SpeedModeCommand( double newSpeedMod, DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        
        speedMod = newSpeedMod;
        addRequirements(drivetrain);
    }

    public void initialize() {
        System.out.println("Initting speed mode. Speed Mod: " + speedMod);
        drivetrain.setSpeedMod(speedMod);
    }

    public void end() {
        System.out.println("Ending speed mode. Prev Speed Mod: " + speedMod);
        drivetrain.setSpeedMod(Constants.NORMAL_SPEED);
    }
}