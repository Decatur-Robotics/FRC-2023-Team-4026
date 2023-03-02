package frc.robot.commands;

import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveDistance extends CommandBase {
    double startTics;
    public double distance;
    public DriveTrainSubsystem driveTrain;
    boolean isFinished = false;
    double motorSpeed = Constants.driveDistanceMotorSpeed;
    public final double DEADBAND_VALUE = Constants.DRIVEDISTANCE_DEADBANDVALUE;

    public DriveDistance(Double distance,DriveTrainSubsystem driveTrain){
        this.startTics = driveTrain.leftDriveFalconFront.getCurrentEncoderValue();
        this.distance = (distance/(Constants.andyMarkHiGripWheelCircumference/Constants.motorRotationsPerWheelRotationGearRatio))*Constants.encoderTicksPerRevolution;
        //= (distance traveled / (circumference of driven wheel or pulley / gear ratio)) * encoder counts per revolution
        this.driveTrain = driveTrain;
    }

    public void initialize() {
        if(distance > 0){
            driveTrain.setMotorPowers(motorSpeed, motorSpeed,"Autonomous says motors go brrrrrrrrrrrr");
        }else if(distance < 0){
            driveTrain.setMotorPowers(-motorSpeed,-motorSpeed,"Atonomous says motors go rrrrrrrrrrrrb");
        }else{
            System.out.println("you make robot go 0 idiot");
            isFinished = true;
        }
    }

    public void execute() {
        if (driveTrain.leftDriveFalconFront.getCurrentEncoderValue() - startTics > distance - DEADBAND_VALUE && driveTrain.leftDriveFalconFront.getCurrentEncoderValue()-startTics < distance + DEADBAND_VALUE){
            driveTrain.setMotorPowers(Constants.stopSpeed, Constants.stopSpeed,"Autonomous says motors stop now (:");
            isFinished = true;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
