package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FindingGillSubsystem;

public class FindingGillRightCommand extends CommandBase {
    FindingGillSubsystem findingGill;
    DriveTrainSubsystem drivetrain;

    public double[] tag1Coordinates = new double[2];
    public double[] tag6Coordinates = new double[2];

    double findingGillMod;

    public FindingGillRightCommand(FindingGillSubsystem findingGill, DriveTrainSubsystem drivetrain) {
        this.findingGill = findingGill;
        this.drivetrain = drivetrain;

        addRequirements(findingGill);
        addRequirements(drivetrain);
    }

    public void execute() {
        tag1Coordinates[0] = 0;
        tag1Coordinates[1] = 0;
        tag6Coordinates[0] = 0;
        tag6Coordinates[1] = 0;

        if (findingGill.tag1Visible == 1) {
            tag1Coordinates[0] = findingGill.tag1X;
            tag1Coordinates[1] = findingGill.tag1Y;
        }
        else {
            tag1Coordinates[0] = 0;
            tag1Coordinates[1] = 0;
        }
        if (findingGill.tag6Visible == 1) {
            tag6Coordinates[0] = findingGill.tag6X;
            tag6Coordinates[1] = findingGill.tag6Y;
        }

        findingGillMod = tag1Coordinates[0] + tag6Coordinates[0];

        drivetrain.setFindingGillMod(findingGillMod);

        

    }
}
