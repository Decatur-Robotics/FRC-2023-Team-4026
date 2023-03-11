package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;


import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SerialPort;

public class AutoBalanceCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;
    AnalogGyro gyro = RobotContainer.gyro;
    double power;
    double stopSpeed = Constants.stopSpeed;

    public AutoBalanceCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;

    }

    public void execute() {
        if (gyro.getAngle() < -1) {
            power = (gyro.getAngle()/Constants.AUTO_BALANCE_DEGREES_TO_MOTOR_POWER_DIVISOR);

            drivetrain.setMotorPowers(power, power, "auto balance");
        }
        else if (gyro.getAngle() > 1) {
            power = -(gyro.getAngle()/Constants.AUTO_BALANCE_DEGREES_TO_MOTOR_POWER_DIVISOR);

            drivetrain.setMotorPowers(power, power, "auto balance");
        }
    }

    public void end() {
        drivetrain.setMotorPowers(stopSpeed, stopSpeed, "auto balance ended");
    }
}
