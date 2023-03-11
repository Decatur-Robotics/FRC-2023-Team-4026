package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class NormalAutoCommand extends CommandBase{
    
    public double drivebackDistance = -50000;

    public static SequentialCommandGroup commands;

    public NormalAutoCommand() {
        System.out.println("Constructed NormalAutoCommand");
    }
    public void initialize() {
        System.out.println("Initializing NormalAutoCommand...");
        // new SetElevatorTargetCommand(RobotContainer.elevator, Constants.topElevatorTargetPosition)
        // new ClawGrabberCommand(RobotContainer.clawIntake, Value.kForward)
        //     .andThen(//new SetElevatorTargetCommand(RobotContainer.elevator, Constants.restElevatorTargetPosition), 
        //     new ClawGrabberCommand(RobotContainer.clawIntake, Value.kReverse), new DriveDistance(drivebackDistance, RobotContainer.drivetrain));

        commands = new ClawGrabberCommand(RobotContainer.clawIntake, Value.kForward)
            .andThen(new SetElevatorTargetCommand(RobotContainer.elevator, Constants.middleElevatorTargetPosition))
            .andThen(new ClawGrabberCommand(RobotContainer.clawIntake, Value.kReverse), 
            new DriveDistance(drivebackDistance, RobotContainer.drivetrain));
        commands.schedule();

    }

    public void execute() {
        System.out.println("Executing NormalAutoCommand...");
    }

    public void end() {
        commands.cancel();
    }
}
