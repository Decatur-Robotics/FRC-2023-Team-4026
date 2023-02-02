package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;

public class ElevatorSubsystem extends SubsystemBase {
    
    public ITeamTalon elevatorMotorMain;
    public ITeamTalon elevatorMotorSub;
    public double motorSpeed = 1;
    public double targetPosition;
    public final double DEADBAND_VALUE = 10;


    public ElevatorSubsystem() {
        elevatorMotorMain = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorMain", Ports.ELEVATOR_MOTOR_MAIN);
        elevatorMotorSub = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorSub", Ports.ELEVATOR_MOTOR_SUB);

        elevatorMotorMain.enableVoltageCompensation(true);
        elevatorMotorSub.enableVoltageCompensation(true);

        elevatorMotorSub.getInverted();

        elevatorMotorMain.setNeutralMode(NeutralMode.Brake);
        elevatorMotorSub.setNeutralMode(NeutralMode.Brake);

        elevatorMotorSub.follow(elevatorMotorMain);
    }
    public void setTargetPosition(double newTargetPosition, String reason) {
        targetPosition = newTargetPosition;
    }

    public void periodic() {
        if (elevatorMotorMain.getCurrentEncoderValue() < targetPosition && elevatorMotorMain.getCurrentEncoderValue() > targetPosition + DEADBAND_VALUE) {
            elevatorMotorMain.set(motorSpeed, "Buttons said so.");
        }
        else if (elevatorMotorMain.getCurrentEncoderValue() > targetPosition && elevatorMotorMain.getCurrentEncoderValue() < targetPosition - DEADBAND_VALUE) {
            elevatorMotorMain.set(-motorSpeed, "Buttons said so.");
        }
    }
    
}
