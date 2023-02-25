package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FindingGillSubsystem;

public class FindingGillCommand extends CommandBase {
    FindingGillSubsystem findingGill;
    DriveTrainSubsystem drivetrain;

    public double[] tag1Coordinates = new double[2];
    public double[] tag2Coordinates = new double[2];
    public double[] tag3Coordinates = new double[2];
    public double[] tag4Coordinates = new double[2];
    public double[] tag5Coordinates = new double[2];
    public double[] tag6Coordinates = new double[2];
    public double[] tag7Coordinates = new double[2];
    public double[] tag8Coordinates = new double[2];

    public double targetTag;

    public double findingGillMod;

    public FindingGillCommand(FindingGillSubsystem findingGill, DriveTrainSubsystem drivetrain, double targetTag) {
        this.findingGill = findingGill;
        this.drivetrain = drivetrain;
        this.targetTag = targetTag;
    }

    public void execute() {
        tag1Coordinates[0] = 0;
        tag1Coordinates[1] = 0;
        tag2Coordinates[0] = 0;
        tag2Coordinates[1] = 0;
        tag3Coordinates[0] = 0;
        tag3Coordinates[1] = 0;
        tag4Coordinates[0] = 0;
        tag4Coordinates[1] = 0;
        tag5Coordinates[0] = 0;
        tag5Coordinates[1] = 0;
        tag6Coordinates[0] = 0;
        tag6Coordinates[1] = 0;
        tag7Coordinates[0] = 0;
        tag7Coordinates[1] = 0;
        tag8Coordinates[0] = 0;
        tag8Coordinates[1] = 0;

        if (findingGill.tag1Visible == 1) {
            tag1Coordinates[0] = findingGill.tag1X;
            tag1Coordinates[1] = findingGill.tag1Y;
        }
        else {
            tag1Coordinates[0] = 0;
            tag1Coordinates[1] = 0;
        }
        if (findingGill.tag2Visible == 1) {
            tag2Coordinates[0] = findingGill.tag2X;
            tag2Coordinates[1] = findingGill.tag2Y;
        }
        else {
            tag2Coordinates[0] = 0;
            tag2Coordinates[1] = 0;
        }
        if (findingGill.tag3Visible == 1) {
            tag3Coordinates[0] = findingGill.tag3X;
            tag3Coordinates[1] = findingGill.tag3Y;
        }
        else {
            tag3Coordinates[0] = 0;
            tag3Coordinates[1] = 0;
        }
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
        if (findingGill.tag6Visible == 1) {
            tag6Coordinates[0] = findingGill.tag6X;
            tag6Coordinates[1] = findingGill.tag6Y;
        }
        else {
            tag6Coordinates[0] = 0;
            tag6Coordinates[1] = 0;
        }
        if (findingGill.tag7Visible == 1) {
            tag7Coordinates[0] = findingGill.tag7X;
            tag7Coordinates[1] = findingGill.tag7Y;
        }
        else {
            tag7Coordinates[0] = 0;
            tag7Coordinates[1] = 0;
        }
        if (findingGill.tag8Visible == 1) {
            tag8Coordinates[0] = findingGill.tag8X;
            tag8Coordinates[1] = findingGill.tag8Y;
        }
        else {
            tag8Coordinates[0] = 0;
            tag8Coordinates[1] = 0;
        }

        
        if (targetTag == 0) {
            //left
            findingGillMod = tag3Coordinates[0] + tag8Coordinates[0];
        } 
        else if (targetTag == 1) {
            //center
            findingGillMod = tag2Coordinates[0] + tag7Coordinates[0];
        }
        else if (targetTag == 2) {
            //right
            findingGillMod = tag1Coordinates[0] + tag6Coordinates[0];
        }
        else if (targetTag == 3) {
            //substation
            findingGillMod = tag4Coordinates[0] + tag5Coordinates[0];
        }


        drivetrain.setFindingGillMod(findingGillMod);
    }
}
