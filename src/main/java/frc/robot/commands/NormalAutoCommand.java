package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class NormalAutoCommand extends CommandBase{
    
    public double drivebackDistance = Constants.normalAutoDriveBackDistance;

    public NormalAutoCommand() {

    }
    public void initialize() {
        new HighElevatorCommand(RobotContainer.elevator)
            .andThen(new ClawGrabberCommand(RobotContainer.clawIntake, Value.kForward))
            .andThen(new RetractedElevatorCommand(RobotContainer.elevator), 
            new ClawGrabberCommand(RobotContainer.clawIntake, Value.kReverse), new DriveDistance(drivebackDistance, RobotContainer.drivetrain));

    }
}
