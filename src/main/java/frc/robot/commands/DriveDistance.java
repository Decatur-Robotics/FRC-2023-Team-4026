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
        this.distance = distance;
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    public void initialize() {
        driveTrain.resetEncoders();

        // this.distance = (distance/(Constants.andyMarkHiGripWheelCircumference/Constants.motorRotationsPerWheelRotationGearRatio))*Constants.encoderTicksPerRevolution;
        //= (distance traveled / (circumference of driven wheel or pulley / gear ratio)) * encoder counts per revolution
  
        System.out.println("Inputted Distance: " + distance + ", Converted Encoder Ticks: " + this.distance);

        System.out.println("Initializing DriveDistance...");
        // driveTrain.enabled = false;W
        driveTrain.leftDriveFalconFront.resetEncoder();
        driveTrain.rightDriveFalconFront.resetEncoder();
        // startTics = 0; // driveTrain.leftDriveFalconFront.getCurrentEncoderValue();
        setPower();
    }

    public void execute() {
        // System.out.println("Executing DriveDistance... Ticks Traveled: "  + ", Distance: " + distance
        //     + ", Deadband: " + DEADBAND_VALUE
        //     + ", Motor Powers: " + driveTrain.leftDriveFalconFront.get()
        //     + ", " + driveTrain.rightDriveFalconFront.get()
        //     + ", Intended Motor Speed: " + motorSpeed
        //     + ", Start Ticks: " + startTics);

         setPower();
    }

    public boolean isFinished() {
        double ticksTraveled =  (driveTrain.leftDriveFalconFront.getCurrentEncoderValue());
        if (Math.abs(ticksTraveled) >= Math.abs(distance))
        {
            return true;
        }
        return false;

    }

    public void end() {
        driveTrain.setDirect(Constants.stopSpeed, Constants.stopSpeed, "Drive Distance ending");
        System.out.println("Ending DriveDistance...");
    }

    public void setPower() {
        if(distance < 0)
        {
            // System.out.println("Setting motor powers backwards...");
            driveTrain.setDirect(
                motorSpeed, motorSpeed,
                "Atonomous says motors go rrrrrrrrrrrrb");
        } else if(distance > 0)
        {
            // System.out.println("Setting motor powers forwards...");
            driveTrain.setDirect(
                -motorSpeed, -motorSpeed,
                "Atonomous says motors go rrrrrrrrrrrrb");
        }



        // if(distance > 0)
        // {
        //     driveTrain.setMotorPowers(motorSpeed, motorSpeed,"Autonomous says motors go brrrrrrrrrrrr");
        // }
        // else if(distance < 0)
        // {
        //     driveTrain.setMotorPowers(-motorSpeed,-motorSpeed,"Atonomous says motors go rrrrrrrrrrrrb");
        // }
        // else
        // {
        //     System.out.println("you make robot go 0 idiot");
        //     isFinished = true;
        // }
    }
}
