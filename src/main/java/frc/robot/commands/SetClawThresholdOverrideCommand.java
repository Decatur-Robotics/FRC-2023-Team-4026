package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetClawThresholdOverrideCommand extends CommandBase {

    public ElevatorSubsystem elevator;
    public boolean value;

    public SetClawThresholdOverrideCommand(boolean value, ElevatorSubsystem elevator) {
        this.value = value;
        this.elevator = elevator;

        addRequirements(elevator);
    }

    public void initialize() {
        elevator.clawThresholdOverridden = value;
    }

    public boolean isFinished() {
        return true;
    }
    
}
