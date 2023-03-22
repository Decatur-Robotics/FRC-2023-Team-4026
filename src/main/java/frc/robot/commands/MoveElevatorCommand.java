package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import java.util.function.DoubleSupplier;

public class MoveElevatorCommand extends CommandBase {

    public ElevatorSubsystem elevator;
    public double input;

    public MoveElevatorCommand(DoubleSupplier in, ElevatorSubsystem elev) {
        elevator = elev;
        input = in.getAsDouble();

        addRequirements(elevator);
    }

    double deadZone(double input) 
    {
        if (Math.abs(input) <= 0.05) 
        {
            return 0;
        }
        else return input;
    }

    public void execute() {
        // System.out.println("Executing move elevator... Input: " + input.getAsDouble());
        if(elevator.targetOverridden)
            input = deadZone(input);

            elevator.setSpeed(Math.pow(input, Constants.ELEVATOR_POWER_EXPONENT));
    }

}