package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveDistance extends CommandBase {
    double startTics;
    double distance;
    public DriveTrainSubsystem driveTrain;

    public DriveDistance(Double distance,DriveTrainSubsystem driveTrain){
        this.startTics = driveTrain.leftDriveFalconFront.getCurrentEncoderValue();
        this.distance = (distance/((0.1524*Math.PI)/(10.71)))*2048;//= (distance traveled / (circumference of driven wheel or pulley / gear ratio)) * encoder counts per revolution
        this.driveTrain = driveTrain;
    }

    public void initialize() {
        driveTrain.setMotorPowers(0.5, 0.5,"Autonomous says motors go brrrrrrrrrrrr");
    }

    public void execute() {
        if (driveTrain.leftDriveFalconFront.getCurrentEncoderValue() > distance-200 && driveTrain.leftDriveFalconFront.getCurrentEncoderValue() < distance+200){
            driveTrain.setMotorPowers(0, 0,"Autonomous says motors stop now (:");
        }
    }
}
