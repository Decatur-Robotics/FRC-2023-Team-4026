package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;
import edu.wpi.first.wpilibj.SerialPort;

public class AutoBalanceCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;
    AHRS gyro;
    double power;
    double stopSpeed = Constants.stopSpeed;

    public AutoBalanceCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;

        AHRS gyro = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte) 50);

    }

    public void execute() {
        if (gyro.getPitch() < -1) {
            power = (gyro.getPitch()/60);

            drivetrain.setMotorPowers(power, power, "auto balance");
        }
        else if (gyro.getPitch() > 1) {
            power = -(gyro.getPitch()/60);

            drivetrain.setMotorPowers(power, power, "auto balance");
        }
    }

    public void end() {
        drivetrain.setMotorPowers(stopSpeed, stopSpeed, "auto balance ended");
    }
}
