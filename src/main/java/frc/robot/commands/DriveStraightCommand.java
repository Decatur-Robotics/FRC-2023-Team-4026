package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveStraightCommand extends CommandBase {

    public DriveTrainSubsystem drivetrain;

    public DriveStraightCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    public void initialize() {
        drivetrain.driveStraight = true;
        drivetrain.leftDriveFalconFront.resetEncoder();
        drivetrain.rightDriveFalconFront.resetEncoder();
    }
    
    public void execute() {
        double left = drivetrain.leftDriveFalconFront.getCurrentEncoderValue(), 
            right = drivetrain.rightDriveFalconFront.getCurrentEncoderValue();

        if(left < right) drivetrain.driveStraightLeftScaler = 1f + (float)(right-left)/2000f;
        else drivetrain.driveStraightLeftScaler = 1;
            
        if(right < left) drivetrain.driveStraightRightScaler = 1f + (float)(left-right)/2000f;
        else drivetrain.driveStraightRightScaler = 1;

        System.out.println("Straight Diff: " + (left-right));
    }

    public void end() {
        drivetrain.driveStraightLeftScaler = 1;
        drivetrain.driveStraightRightScaler = 1;
        drivetrain.driveStraight = false;
    }

}