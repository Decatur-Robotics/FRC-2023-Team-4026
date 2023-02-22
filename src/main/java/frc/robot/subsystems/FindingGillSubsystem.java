package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.TeamUtils;

public class FindingGillSubsystem extends SubsystemBase {
    public double numberOfApriltags;

    public double tag1Visible;
    public double tag2Visible;
    public double tag3Visible;
    public double tag4Visible;
    public double tag5Visible;
    public double tag6Visible;
    public double tag7Visible;
    public double tag8Visible;

    public double tag1X;
    public double tag1Y;
    public double tag2X;
    public double tag2Y;
    public double tag3X;
    public double tag3Y;
    public double tag4X;
    public double tag4Y;
    public double tag5X;
    public double tag5Y;
    public double tag6X;
    public double tag6Y;
    public double tag7X;
    public double tag7Y;
    public double tag8X;
    public double tag8Y;

    public void Periodic() {
        this.numberOfApriltags = (double) TeamUtils.getFromNetworkTable("apriltags", "Tags");
        
        this.tag1Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 Visible");
        this.tag2Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 Visible");
        this.tag3Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 Visible");
        this.tag4Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 Visible");
        this.tag5Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 Visible");
        this.tag6Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 Visible");
        this.tag7Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 Visible");
        this.tag8Visible = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 Visible");

        this.tag1X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 X");
        this.tag1Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 1 Y");
        this.tag2X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 X");
        this.tag2Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 2 Y");
        this.tag3X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 X");
        this.tag3Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 3 Y");
        this.tag4X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 X");
        this.tag4Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 4 Y");
        this.tag5X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 X");
        this.tag5Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 5 Y");
        this.tag6X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 X");
        this.tag6Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 6 Y");
        this.tag7X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 X");
        this.tag7Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 7 Y");
        this.tag8X = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 X");
        this.tag8Y = (double) TeamUtils.getFromNetworkTable("apriltags", "Tag 8 Y");
    }
}
