package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FindingGillSubsystem;

public class FindingGillCenterCommand extends CommandBase {
    FindingGillSubsystem findingGill;
    DriveTrainSubsystem drivetrain;

    public double[] tag2Coordinates = new double[2];
    public double[] tag7Coordinates = new double[2];

    public double findingGillMod;

    public FindingGillCenterCommand(FindingGillSubsystem findingGill, DriveTrainSubsystem drivetrain) {
        this.findingGill = findingGill;
        this.drivetrain = drivetrain;

        addRequirements(findingGill);
        addRequirements(drivetrain);
    }

    public void execute() {
        tag2Coordinates[0] = 0;
        tag2Coordinates[1] = 0;
        tag7Coordinates[0] = 0;
        tag7Coordinates[1] = 0;

        if (findingGill.tag2Visible == 1) {
            tag2Coordinates[0] = findingGill.tag2X;
            tag2Coordinates[1] = findingGill.tag2Y;
        }
        else {
            tag2Coordinates[0] = 0;
            tag2Coordinates[1] = 0;
        }
        if (findingGill.tag7Visible == 1) {
            tag7Coordinates[0] = findingGill.tag7X;
            tag7Coordinates[1] = findingGill.tag7Y;
        }
        else {
            tag7Coordinates[0] = 0;
            tag7Coordinates[1] = 0;
        }

        findingGillMod = tag2Coordinates[0] + tag7Coordinates[0];

        drivetrain.setFindingGillMod(findingGillMod);

    }
}
