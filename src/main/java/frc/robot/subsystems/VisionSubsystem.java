package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.TeamUtils;

public class VisionSubsystem extends SubsystemBase {
    // public double numberOfApriltags;

    // public double tag1Visible;
    // public double tag2Visible;
    // public double tag3Visible;
    // public double tag4Visible;
    // public double tag5Visible;
    // public double tag6Visible;
    // public double tag7Visible;
    // public double tag8Visible;

    // public double tag1X;
    // public double tag1Y;
    // public double tag2X;
    // public double tag2Y;
    // public double tag3X;
    // public double tag3Y;
    // public double tag4X;
    // public double tag4Y;
    // public double tag5X;
    // public double tag5Y;
    // public double tag6X;
    // public double tag6Y;
    // public double tag7X;
    // public double tag7Y;
    // public double tag8X;
    // public double tag8Y;

    // public double tag1Width;
    // public double tag2Width;
    // public double tag3Width;
    // public double tag4Width;
    // public double tag5Width;
    // public double tag6Width;
    // public double tag7Width;
    // public double tag8Width;

    public double coneX;
    public double coneY;

    public int coneVisible;

    public double cubeX;
    public double cubeY;

    public int cubeVisible;

    public void Periodic() {
        // numberOfApriltags = (double) TeamUtils.getFromNetworkTable("apriltags", "Tags");
        
        // tag1Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 Visible");
        // tag2Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 Visible");
        // tag3Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 Visible");
        // tag4Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 Visible");
        // tag5Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 Visible");
        // tag6Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 Visible");
        // tag7Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 Visible");
        // tag8Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 Visible");

        // tag1X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 X");
        // tag1Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 Y");
        // tag2X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 X");
        // tag2Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 Y");
        // tag3X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 X");
        // tag3Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 Y");
        // tag4X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 X");
        // tag4Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 Y");
        // tag5X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 X");
        // tag5Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 Y");
        // tag6X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 X");
        // tag6Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 Y");
        // tag7X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 X");
        // tag7Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 Y");
        // tag8X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 X");
        // tag8Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 Y");

        // tag1Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 Width");
        // tag2Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 Width");
        // tag3Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 Width");
        // tag4Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 Width");
        // tag5Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 Width");
        // tag6Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 Width");
        // tag7Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 Width");
        // tag8Width = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 Width");

        coneX = (double) TeamUtils.getFromNetworkTable("cones", "Cone X");
        coneY = (double) TeamUtils.getFromNetworkTable("cones", "Cone Y");
        coneVisible = (int) TeamUtils.getFromNetworkTable("cones", "Cone Visible");

        cubeX = (double) TeamUtils.getFromNetworkTable("cubes", "Cube X");
        cubeY = (double) TeamUtils.getFromNetworkTable("cubes", "Cube Y");
        cubeVisible = (int) TeamUtils.getFromNetworkTable("cubes", "Cube Visible");
    }
}
