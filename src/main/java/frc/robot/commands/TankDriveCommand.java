package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TankDriveCommand extends CommandBase {
    DriveTrainSubsystem driveTrain;
    DoubleSupplier leftStick, rightStick;


    
    public TankDriveCommand(DoubleSupplier leftStick, DoubleSupplier rightStick, DriveTrainSubsystem driveTrain) 
    {
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.driveTrain = driveTrain;

        addRequirements(driveTrain);
    }

    double deadZone(double input) 
    {
        if (Math.abs(input) <= 0.05) 
        {
            return 0;
        }
        else return input;
    }

    double cubePower(double input)
    {
        return Math.pow(input, Constants.DRIVE_TRAIN_POWER_EXPONENT);
    }
    
    public void execute() 
    {
        // System.out.println("Left Input: " + leftStick.getAsDouble() + ", Right Input: " + rightStick.getAsDouble());
        driveTrain.setMotorPowers(cubePower(deadZone(leftStick.getAsDouble())), cubePower(deadZone(rightStick.getAsDouble())), "Joysticks said so");
    }
}
