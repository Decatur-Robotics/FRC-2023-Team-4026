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

    double minAngle = 3;

    double angle;

    double balanceSpeed = 0.25;

    int crossOver = 1;

    double minSpeed = 0.1;

    public AutoBalanceCommand(DriveTrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        balanceSpeed = 0.25;
        crossOver = 1;
        addRequirements(drivetrain);

        ShuffleboardTab tab = RobotContainer.shuffleboard;

        tab.addDouble("Balance Speed", ()->balanceSpeed);

    }

    public void execute() {
        angle = RobotContainer.balanceGyro.getAngle();

        if (angle > minAngle) {
            if (crossOver == -1) {
                balanceSpeed /= 2;
            }
            crossOver = 1;

            drivetrain.setMotorPowers(Math.max(balanceSpeed, minSpeed), Math.max(balanceSpeed, minSpeed), "auto balance");
        }
        else if (angle < -minAngle) {
            if (crossOver == 1) {
                balanceSpeed /= 2;
            }
            crossOver = -1;

            drivetrain.setMotorPowers(-Math.max(balanceSpeed, minSpeed), -Math.max(balanceSpeed, minSpeed), "auto balance");
        }
        else {
            drivetrain.setMotorPowers(0, 0, "auto balance end");
        }
    }

    public void end() {
        angle = 0;
    }
}
