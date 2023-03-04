package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorTargetCommand extends CommandBase {
    ElevatorSubsystem elevator;

    public long targetPosition;

    public SetElevatorTargetCommand(ElevatorSubsystem elevator, long targetPosition) {
        this.elevator = elevator;
        this.targetPosition = targetPosition;
        addRequirements(elevator);
    }

    public void initialize() {
        elevator.setTargetPosition(targetPosition, "Button said so");
    }
}
