package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;


import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class AutoBalanceCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;
    double power;
    double stopSpeed = Constants.stopSpeed;

    double angle;

    double balanceSpeed = 0.2;

    int crossOver = 1;

    double previousAngle = 0;

    public AutoBalanceCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        balanceSpeed = 0.2;
        crossOver = 1;
        addRequirements(drivetrain);

        ShuffleboardTab tab = RobotContainer.shuffleboard;

        tab.addDouble("Balance Speed", ()->balanceSpeed);

    }

    public void execute() {
        angle = RobotContainer.balanceGyro.getAngle();

        if (angle > Constants.AUTO_BALANCE_MINIMUM_ANGLE) {
            if (crossOver == -1) {
                balanceSpeed /= 2;
            }

            if (angle < previousAngle - Constants.AUTO_BALANCE_FALL_ANGLE_SIZE) {
                drivetrain.setMotorPowers(0, 0, "auto balance");
            }
            else {
                drivetrain.setMotorPowers(-Math.max(balanceSpeed * (Math.abs(angle) / Constants.AUTO_BALANCE_ANGLE_DIVISOR), Constants.AUTO_BALANCE_MINIMUM_SPEED), Math.max(balanceSpeed, Constants.AUTO_BALANCE_MINIMUM_SPEED), "auto balance");

            }
            
            crossOver = 1;
        }
        else if (angle < -Constants.AUTO_BALANCE_MINIMUM_ANGLE) {
            if (crossOver == 1) {
                balanceSpeed /= 2;
            }

            if (angle < previousAngle - Constants.AUTO_BALANCE_FALL_ANGLE_SIZE) {
                drivetrain.setMotorPowers(0, 0, "auto balance");
            }
            else {
                drivetrain.setMotorPowers(Math.max(balanceSpeed * (Math.abs(angle) / Constants.AUTO_BALANCE_ANGLE_DIVISOR), Constants.AUTO_BALANCE_MINIMUM_SPEED), Math.max(balanceSpeed, Constants.AUTO_BALANCE_MINIMUM_SPEED), "auto balance");

            }

            crossOver = -1;
        }
        else {
            drivetrain.setMotorPowers(0, 0, "auto balance end");
        }

        previousAngle = angle;
    }

    public void end() {
        angle = 0;
    }
}
