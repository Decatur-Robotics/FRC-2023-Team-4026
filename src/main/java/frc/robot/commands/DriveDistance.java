package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveDistance extends CommandBase {
    double startTics;
    public double distance;
    public DriveTrainSubsystem driveTrain;
    boolean isFinished = false;

    public DriveDistance(Double distance,DriveTrainSubsystem driveTrain){
        this.startTics = driveTrain.leftDriveFalconFront.getCurrentEncoderValue();
        this.distance = (distance/((0.1524*Math.PI)/(10.71)))*2048;//= (distance traveled / (circumference of driven wheel or pulley / gear ratio)) * encoder counts per revolution
        this.driveTrain = driveTrain;
    }

    public void initialize() {
        if(distance > 0){
            driveTrain.setMotorPowers(0.5, 0.5,"Autonomous says motors go brrrrrrrrrrrr");
        }else if(distance < 0){
            driveTrain.setMotorPowers(-0.5,-0.5,"Atonomous says motors go rrrrrrrrrrrrb");
        }else{
            System.out.println("you make robot go 0 idiot");
            isFinished = true;
        }
    }

    public void execute() {
        if (driveTrain.leftDriveFalconFront.getCurrentEncoderValue() - startTics > distance-200 && driveTrain.leftDriveFalconFront.getCurrentEncoderValue()-startTics < distance+200){
            driveTrain.setMotorPowers(0, 0,"Autonomous says motors stop now (:");
            isFinished = true;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
