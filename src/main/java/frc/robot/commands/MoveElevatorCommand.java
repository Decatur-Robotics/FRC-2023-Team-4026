package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import java.util.function.DoubleSupplier;

public class MoveElevatorCommand extends CommandBase {

    public ElevatorSubsystem elevator;
    public DoubleSupplier input;

    public MoveElevatorCommand(DoubleSupplier in, ElevatorSubsystem elev) {
        elevator = elev;
        input = in;

        addRequirements(elev);
    }

    public void execute() {
        elevator.setSpeed(input.getAsDouble());
    }

}