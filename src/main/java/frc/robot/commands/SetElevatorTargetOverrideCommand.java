package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorTargetOverrideCommand extends CommandBase {

    public ElevatorSubsystem elevator;
    public boolean value;

    public SetElevatorTargetOverrideCommand(boolean value, ElevatorSubsystem elevator) {
        this.value = value;
        this.elevator = elevator;

        addRequirements(elevator);
    }

    public void initialize() {
        elevator.targetOverridden = value;

        if(!value) {
            elevator.resetTarget();
            elevator.setSpeed(0);
        }
    }

    public boolean isFinished() {
        return true;
    }
    
}
