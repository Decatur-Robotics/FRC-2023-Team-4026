package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TurnDegrees extends CommandBase {
    final double startDeg = 0;//get degree in gyro once Complete
    double endDeg;
    public DriveTrainSubsystem driveTrain;
    
    public TurnDegrees(double changeDeg){
        this.endDeg = startDeg + changeDeg;
        
    }

    public void initialize() {
        if(endDeg-startDeg < 0 && Math.abs(endDeg-startDeg)<180){
            //right
        }else{
            //left
        }
    }

    public void execute() {
        
    }
}
