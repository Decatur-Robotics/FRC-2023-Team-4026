package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class SetFindingGillSideCommand extends CommandBase {
    DriveTrainSubsystem drivetrain;

    public double side;

    public SetFindingGillSideCommand(DriveTrainSubsystem drivetrain, double side) {
        this.drivetrain = drivetrain;
        this.side = side;
    }

    public void initialize() {
        drivetrain.setFindingGillSide(side);
    }

    public void end() {
        drivetrain.setFindingGillSide(Constants.FINDING_GILL_SIDE_CENTER);
    }
}
