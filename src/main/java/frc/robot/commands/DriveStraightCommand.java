package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveStraightCommand extends CommandBase {

    public DriveTrainSubsystem drivetrain;
    public AHRS gyro;
    double startYaw;

    public DriveStraightCommand(DriveTrainSubsystem drivetrains) {
        this.drivetrain = drivetrain;
        gyro = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte) 50);
    }

    public void initialize() {
        startYaw = gyro.getYaw();
        drivetrain.driveStraight = true;
    }
    
    public void execute() {
        if (gyro.getYaw() < startYaw) {
            drivetrain.driveStraightLeftScaler = 1.1f;
        } 
        else if (gyro.getYaw() > startYaw) {
            drivetrain.driveStraightRightScaler = 1.1f;
        }
        else {
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