package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorTargetCommand extends CommandBase {
    ElevatorSubsystem elevator;

    public double targetPosition;

    public SetElevatorTargetCommand( double targetPosition,ElevatorSubsystem elevator) {
        this.elevator = elevator;
        this.targetPosition = targetPosition;
        addRequirements(elevator);
    }

    public void initialize() {
        elevator.setTargetPosition(targetPosition, "Button said so");
    }

    public boolean isFinished() {
        return true;
    }
}
