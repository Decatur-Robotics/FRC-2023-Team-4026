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
        System.out.println("Constructing DriveDistance...");
        this.startTics = driveTrain.leftDriveFalconFront.getCurrentEncoderValue();
        this.distance = (distance/(Constants.andyMarkHiGripWheelCircumference/Constants.motorRotationsPerWheelRotationGearRatio))*Constants.encoderTicksPerRevolution;
        //= (distance traveled / (circumference of driven wheel or pulley / gear ratio)) * encoder counts per revolution
        this.driveTrain = driveTrain;
        System.out.println("Inputted Distance: " + distance + ", Converted Encoder Ticks: " + this.distance);
    }

    public void initialize() {
        System.out.println("Initializing DriveDistance...");
        setPower();
    }

    public void execute() {
        long ticksRemaing = (long) (driveTrain.leftDriveFalconFront.getCurrentEncoderValue() - startTics);
        System.out.println("Executing DriveDistance... Ticks Remaining: " + ticksRemaing);
        if (ticksRemaing > distance - DEADBAND_VALUE && 
            ticksRemaing < distance + DEADBAND_VALUE)
        {
            driveTrain.setMotorPowers(Constants.stopSpeed, Constants.stopSpeed,"Autonomous says motors stop now (:");
            isFinished = true;
        }
        else setPower();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void end() {        
        System.out.println("Ending DriveDistance...");
    }

    public void setPower() {
        if(distance > 0)
        {
            driveTrain.setMotorPowers(motorSpeed, motorSpeed,"Autonomous says motors go brrrrrrrrrrrr");
        }
        else if(distance < 0)
        {
            driveTrain.setMotorPowers(-motorSpeed,-motorSpeed,"Atonomous says motors go rrrrrrrrrrrrb");
        }
        else
        {
            System.out.println("you make robot go 0 idiot");
            isFinished = true;
        }
    }
}
