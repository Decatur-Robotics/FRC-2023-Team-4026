package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class VisionCommand extends CommandBase {
    VisionSubsystem vision;
    DriveTrainSubsystem drivetrain;

    public double coneX;
    public double coneY;
    public double coneVisible;

    public double visionMod;

    public VisionCommand(VisionSubsystem vision, DriveTrainSubsystem drivetrain ) {
        this.vision = vision;
        this.drivetrain = drivetrain;
        addRequirements(vision);
    }

    public void execute() {
        coneX = vision.coneX;
        coneY = vision.coneY;
        coneVisible = vision.coneVisible;
            
        if (coneVisible == 1) {
            visionMod = coneX;
            drivetrain.setVisionMod(true, visionMod);
        }
        else {
            visionMod = 0;
            drivetrain.setVisionMod(false, visionMod);
        }
    }

    public void end() {
        drivetrain.setVisionMod(false, 0);
    }
}