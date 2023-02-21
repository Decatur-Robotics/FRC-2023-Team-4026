package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FindingGillSubsystem;

public class FindingGillLeftCommand extends CommandBase {
    FindingGillSubsystem findingGill;
    DriveTrainSubsystem drivetrain;

    public double[] tag3Coordinates = new double[2];
    public double[] tag8Coordinates = new double[2];

    double findingGillMod;

    public FindingGillLeftCommand(FindingGillSubsystem findingGill, DriveTrainSubsystem drivetrain) {
        this.findingGill = findingGill;
        this.drivetrain = drivetrain;

        addRequirements(findingGill);
        addRequirements(drivetrain);
    }

    public void execute() {
        tag3Coordinates[0] = 0;
        tag3Coordinates[1] = 0;
        tag8Coordinates[0] = 0;
        tag8Coordinates[1] = 0;

        if (findingGill.tag3Visible == 1) {
            tag3Coordinates[0] = findingGill.tag3X;
            tag3Coordinates[1] = findingGill.tag3Y;
        }
        else {
            tag3Coordinates[0] = 0;
            tag3Coordinates[1] = 0;
        }
        if (findingGill.tag8Visible == 1) {
            tag8Coordinates[0] = findingGill.tag8X;
            tag8Coordinates[1] = findingGill.tag8Y;
        }
        else {
            tag8Coordinates[0] = 0;
            tag8Coordinates[1] = 0;
        }

        findingGillMod = tag3Coordinates[0] + tag8Coordinates[0];

        drivetrain.setFindingGillMod(findingGillMod);

        

    }
}
