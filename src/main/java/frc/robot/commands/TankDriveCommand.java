package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
    
    public void execute() 
    {
        // System.out.println("Left Input: " + leftStick.getAsDouble() + ", Right Input: " + rightStick.getAsDouble());
        driveTrain.setMotorPowers(deadZone(leftStick.getAsDouble()), deadZone(rightStick.getAsDouble()), "Joysticks said so");
    }
}
