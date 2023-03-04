package frc.robot.commands;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TurnDegrees extends CommandBase {
    AnalogGyro gyro;
    final double startDeg;//get degree in gyro once Complete
    double endDeg;
    DriveTrainSubsystem driveTrain;
    boolean isFinished = false;
    double motorSpeed = Constants.turnDegreesMotorSpeed;
    double stopSpeed = Constants.stopSpeed;
    
    public TurnDegrees(double changeDeg, DriveTrainSubsystem driveTrain, AnalogGyro gyro){
        this.gyro = gyro;
        this.startDeg = this.gyro.getAngle();
        this.endDeg = startDeg + changeDeg;
        this.driveTrain = driveTrain;
        
    }
    
    
    public void initialize() {
        if(endDeg-startDeg < 0 && Math.abs(endDeg-startDeg)<180){
            driveTrain.setMotorPowers(motorSpeed,-motorSpeed,"Autonomous says beep boop robo-TURN right");
        }else{
            driveTrain.setMotorPowers(-motorSpeed, motorSpeed, "Autonomous says beep boop robo-TURN left");
        }
    }

    public void execute() {
        double angle = gyro.getAngle();
        double accuracy = 1;
        if(angle < endDeg + accuracy && angle > endDeg - accuracy){
            driveTrain.setMotorPowers(stopSpeed, stopSpeed, "Autonomous says beep beep robo-TURN done now (:");
            isFinished = true;
        }
    }

    public boolean isFinished(){
        return isFinished;
    }
}
