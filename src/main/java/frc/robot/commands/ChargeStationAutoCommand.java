package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.SetElevatorTargetCommand;

public class ChargeStationAutoCommand extends CommandBase{
    
    public double drivebackDistance = Constants.chargeStationAutoDriveBackDistance;
    public double driveForwardDistance = Constants.chargeStationAutoDriveForwardDistance;
    public ChargeStationAutoCommand() {

    }

    public void initialize() {
        new SetElevatorTargetCommand(RobotContainer.elevator, Constants.topElevatorTargetPosition)
            .andThen(new ClawGrabberCommand(RobotContainer.clawIntake, Value.kForward))
            .andThen(new SetElevatorTargetCommand(RobotContainer.elevator, Constants.restElevatorTargetPosition), 
            new ClawGrabberCommand(RobotContainer.clawIntake, Value.kReverse), new DriveDistance(drivebackDistance, RobotContainer.drivetrain))
            .andThen(new DriveDistance(driveForwardDistance, RobotContainer.drivetrain))
            .andThen(new AutoBalanceCommand(RobotContainer.drivetrain));

    }
}
