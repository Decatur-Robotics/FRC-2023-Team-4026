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
    }

    public void initialize() {
        driveTrain.resetEncoders();

        this.startTics = driveTrain.leftDriveFalconFront.getCurrentEncoderValue();
        this.distance = (distance/(Constants.andyMarkHiGripWheelCircumference/Constants.motorRotationsPerWheelRotationGearRatio))*Constants.encoderTicksPerRevolution;
        //= (distance traveled / (circumference of driven wheel or pulley / gear ratio)) * encoder counts per revolution
        this.distance = -50000;
        this.driveTrain = driveTrain;
        System.out.println("Inputted Distance: " + distance + ", Converted Encoder Ticks: " + this.distance);

        System.out.println("Initializing DriveDistance...");
        driveTrain.enabled = false;
        setPower();
    }

    public void execute() {
        long ticksTraveled = (long) (driveTrain.leftDriveFalconFront.getCurrentEncoderValue() - startTics);
        System.out.println("Executing DriveDistance... Ticks Traveled: " + ticksTraveled + ", Distance: " + distance
            + ", Deadband: " + DEADBAND_VALUE
            + ", Motor Powers: " + driveTrain.leftDriveFalconFront.get()
            + ", " + driveTrain.rightDriveFalconFront.get()
            + ", Intended Motor Speed: " + motorSpeed
            + ", Start Ticks: " + startTics);
        if ((ticksTraveled > distance && distance > 0) ||
            (ticksTraveled < distance && distance < 0))
        {
            driveTrain.setDirect(Constants.stopSpeed, Constants.stopSpeed,"Autonomous says motors stop now (:");
            isFinished = true;
        }
        else setPower();
    }

    public boolean isFinished() {
        long ticksTraveled = (long) (driveTrain.leftDriveFalconFront.getCurrentEncoderValue() - startTics);
        return isFinished || (ticksTraveled > distance && distance > 0) ||
        (ticksTraveled < distance && distance < 0);
    }

    public void end() {
        driveTrain.enabled = true;
        System.out.println("Ending DriveDistance...");
    }

    public void setPower() {
        if(distance < 0)
        {
            System.out.println("Setting motor powers backwards...");
            driveTrain.setDirect(
                motorSpeed, motorSpeed,
                "Atonomous says motors go rrrrrrrrrrrrb");
        } else if(distance > 0)
        {
            System.out.println("Setting motor powers forwards...");
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
