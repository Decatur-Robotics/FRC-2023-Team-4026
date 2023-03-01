package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class HighElevatorCommand extends CommandBase {
    ElevatorSubsystem elevator;

    public long targetPosition = 15;

    public HighElevatorCommand(ElevatorSubsystem elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    public void initialize() {
        elevator.setTargetPosition(targetPosition, "Button said so");
    }
}
