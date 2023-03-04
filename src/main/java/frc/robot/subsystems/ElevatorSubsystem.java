package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;

public class ElevatorSubsystem extends SubsystemBase {
    
    public ITeamTalon elevatorMotorMain;
    // public ITeamTalon elevatorMotorSub;
    public double motorSpeed = Constants.elevatorMotorSpeed;
    public double targetPosition;
    public final double DEADBAND_VALUE = Constants.ELEVATOR_DEADBAND_VALUE;


    public ElevatorSubsystem() {
        elevatorMotorMain = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorMain", Ports.ELEVATOR_MOTOR_MAIN);
        // elevatorMotorSub = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorSub", Ports.ELEVATOR_MOTOR_SUB);

        elevatorMotorMain.enableVoltageCompensation(true);
        // elevatorMotorSub.enableVoltageCompensation(true);

        elevatorMotorMain.setNeutralMode(NeutralMode.Brake);
        // elevatorMotorSub.setNeutralMode(NeutralMode.Brake);

        // elevatorMotorSub.follow(elevatorMotorMain);
    }
    public void setTargetPosition(double newTargetPosition, String reason) {
        targetPosition = newTargetPosition;
    }

    public void setSpeed(double speed) {
        if(Math.abs(speed) > 0.05)
            elevatorMotorMain.set(speed, "Joystick said so");
        else elevatorMotorMain.set(0, "Stopping elevator");
    }

    // public void periodic() {
    //     System.out.println("Target Pos: " + targetPosition);
        
    //     if (elevatorMotorMain.getCurrentEncoderValue() < targetPosition && elevatorMotorMain.getCurrentEncoderValue() > targetPosition + DEADBAND_VALUE) {
    //         elevatorMotorMain.set(motorSpeed, "Buttons said so.");
    //     }
    //     else if (elevatorMotorMain.getCurrentEncoderValue() > targetPosition && elevatorMotorMain.getCurrentEncoderValue() < targetPosition - DEADBAND_VALUE) {
    //         elevatorMotorMain.set(-motorSpeed, "Buttons said so.");
    //     }
    //     else {
    //         elevatorMotorMain.set(0, "Stopping elevator");
    //     }
    // }
    
}
