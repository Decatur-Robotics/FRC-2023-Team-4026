package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveStraightCommand extends CommandBase {

    public DriveTrainSubsystem drivetrain;
    public boolean enable;

    public DriveStraightCommand(boolean enable, DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        this.enable = enable;
        drivetrain.driveStraight = false;
    }

    public void initialize() {
        if(enable) {
            drivetrain.driveStraight = true;
            drivetrain.leftDriveFalconFront.resetEncoder();
            drivetrain.rightDriveFalconFront.resetEncoder();
            RobotContainer.gyro.reset();
        } else {
            drivetrain.alignLeftScaler = 1;
            drivetrain.alignRightScaler = 1;
            drivetrain.driveStraight = false;
        }
    }

    public boolean isFinished() {
        return true;
    }

}