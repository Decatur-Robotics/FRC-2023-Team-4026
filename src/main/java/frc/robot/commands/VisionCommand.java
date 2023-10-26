package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class VisionCommand extends CommandBase {
    VisionSubsystem vision;
    SwerveDriveSubsystem drivetrain;

    public double coneX;
    public int coneVisible;

    public double cubeX;
    public int cubeVisible;

    public double visionMod;

    public boolean enable = false;

    public VisionCommand(boolean enable, VisionSubsystem vision, SwerveDriveSubsystem drivetrain ) {
        this.enable = enable;
        this.vision = vision;
        this.drivetrain = drivetrain;
        addRequirements(vision);
    }

    public void initialize() {
    }

    // public void execute() {
    //     coneX = vision.coneX;
    //     coneVisible = vision.coneVisible;

    //     cubeX = vision.cubeX;
    //     cubeVisible = vision.cubeVisible;
            
    //     if (coneVisible == 1) {
    //         visionMod = coneX;
    //         drivetrain.autoAlign = true; 
    //         drivetrain.visionX = visionMod;
    //     }
    //     else if (cubeVisible == 1) {
    //         visionMod = cubeX;
    //         drivetrain.autoAlign = true; 
    //         drivetrain.visionX = visionMod;
    //     }
    //     else {
    //         visionMod = 0;
    //         drivetrain.autoAlign = false; 
    //         drivetrain.visionX = visionMod;
    //     }
    // }

    // public void end() {
    //     drivetrain.autoAlign = false; 
    //     drivetrain.visionX = 0;
    // }

    public boolean isFinished() {
        return true;
    }
}
