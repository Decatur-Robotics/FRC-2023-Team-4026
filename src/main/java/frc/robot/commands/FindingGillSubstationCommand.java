package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FindingGillSubsystem;

public class FindingGillSubstationCommand extends CommandBase {
    FindingGillSubsystem findingGill;
    DriveTrainSubsystem drivetrain;

    public double[] tag4Coordinates = new double[2];
    public double[] tag5Coordinates = new double[2];

    double findingGillMod;

    public FindingGillSubstationCommand(FindingGillSubsystem findingGill, DriveTrainSubsystem drivetrain) {
        this.findingGill = findingGill;
        this.drivetrain = drivetrain;

        addRequirements(findingGill);
        addRequirements(drivetrain);
    }

    public void execute() {
        tag4Coordinates[0] = 0;
        tag4Coordinates[1] = 0;
        tag5Coordinates[0] = 0;
        tag5Coordinates[1] = 0;

        if (findingGill.tag4Visible == 1) {
            tag4Coordinates[0] = findingGill.tag4X;
            tag4Coordinates[1] = findingGill.tag4Y;
        }
        else {
            tag4Coordinates[0] = 0;
            tag4Coordinates[1] = 0;
        }
        if (findingGill.tag5Visible == 1) {
            tag5Coordinates[0] = findingGill.tag5X;
            tag5Coordinates[1] = findingGill.tag5Y;
        }
        else {
            tag5Coordinates[0] = 0;
            tag5Coordinates[1] = 0;
        }

        findingGillMod = tag4Coordinates[0] + tag5Coordinates[0];

        drivetrain.setFindingGillMod(findingGillMod);

        

    }
}
