package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class NormalAutoCommand extends CommandBase{
    



    public NormalAutoCommand() {

    }

    public void initialize() {
        new HighElevatorCommand(RobotContainer.elevator)
            .andThen(new ClawGrabberCommand(RobotContainer.clawIntake, Value.kForward))
            .andThen(new BottomElevatorCommand(RobotContainer.elevator), new ClawGrabberCommand(RobotContainer.clawIntake, Value.kReverse), new DriveDistance(-5.8, RobotContainer.drivetrain));

    }
}
