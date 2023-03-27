package frc.robot.commands;

import java.time.LocalTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorTargetCommand extends CommandBase {
    ElevatorSubsystem elevator;

    public double targetPosition;
    public boolean wait;

    public SetElevatorTargetCommand(double targetPosition, ElevatorSubsystem elevator) {
        this.elevator = elevator;
        this.targetPosition = targetPosition;
        this.wait = false;
        addRequirements(elevator);
    }

    public SetElevatorTargetCommand(double targetPosition, boolean wait, ElevatorSubsystem elevator) {
        this.elevator = elevator;
        this.targetPosition = targetPosition;
        this.wait = wait;
        addRequirements(elevator);
    }

    public void initialize() {
        System.out.println("Setting elevator target to " + targetPosition + "...");
        elevator.setTargetPosition(targetPosition, "Button said so");
    }

    public boolean isFinished() {
        return !wait || elevator.isInTarget();
    }
}
