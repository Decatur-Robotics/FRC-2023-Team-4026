package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorTargetCommand extends CommandBase {
    ElevatorSubsystem elevator;

    public double targetPosition;
    boolean initialized;

    LocalTime startTime;
    long timeToWait = 2000000000;

    public SetElevatorTargetCommand(ElevatorSubsystem elevator, double targetPosition) {
        this.elevator = elevator;
        this.targetPosition = targetPosition;
        addRequirements(elevator);
        
        startTime = LocalTime.now();
    }

    public void initialize() {
        elevator.setTargetPosition(targetPosition, "Button said so");
        initialized = true;
    }

    public boolean isFinished() {
        return initialized && 
            LocalTime.now().minusNanos(timeToWait).compareTo(startTime) > 0;
    }
}
