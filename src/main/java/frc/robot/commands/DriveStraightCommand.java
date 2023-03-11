package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveStraightCommand extends CommandBase {

    public DriveTrainSubsystem drivetrain;
    public BooleanSupplier input;

    public DriveStraightCommand(DriveTrainSubsystem drivetrain, BooleanSupplier input) {
        this.drivetrain = drivetrain;
        this.input = input;
    }

    public void initialize() {
        drivetrain.driveStraight = true;
        drivetrain.leftDriveFalconFront.resetEncoder();
        drivetrain.rightDriveFalconFront.resetEncoder();
    }
    
    public void execute() {
        if(input.getAsBoolean()) {
            long left = drivetrain.leftDriveFalconFront.getCurrentEncoderValue(), 
                right = drivetrain.rightDriveFalconFront.getCurrentEncoderValue();

            if(left > right) drivetrain.driveStraightLeftScaler = 1f + (float)(left-right)/5000f;
            else drivetrain.driveStraightLeftScaler = 1;
            
            if(right > left) drivetrain.driveStraightRightScaler = 1f + (float)(right-left)/5000f;
            else drivetrain.driveStraightRightScaler = 1;

            System.out.println("Straight Diff: " + (left-right));
        }
        else
        {
            drivetrain.driveStraight = false;
            drivetrain.driveStraightLeftScaler = 1;
            drivetrain.driveStraightRightScaler = 1;
        }
    }

    public void end() {
        drivetrain.driveStraightLeftScaler = 1;
        drivetrain.driveStraightRightScaler = 1;
        drivetrain.driveStraight = false;
    }

}