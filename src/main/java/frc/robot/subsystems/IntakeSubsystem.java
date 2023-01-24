package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;

public class IntakeSubsystem extends SubsystemBase {
    
    
    public ITeamTalon intakeMotor;
    public double targetPosition; 
    public double motorSpeed = 1;
    public final double DEADBAND_VALUE = 10;


    public IntakeSubsystem() {
        intakeMotor = new TeamTalonFX("Subsystem.Intake.IntakeMotor", Ports.INTAKE_MOTOR);

        intakeMotor.enableVoltageCompensation(true);

        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setTargetPosition(double newTargetPosition, String reason) {
        targetPosition = newTargetPosition;
    }

    public void periodic() {
        if (intakeMotor.getCurrentEncoderValue() < targetPosition && intakeMotor.getCurrentEncoderValue() > targetPosition + DEADBAND_VALUE) {
            intakeMotor.set(motorSpeed, "Buttons said so.");
        }
        else if (intakeMotor.getCurrentEncoderValue() > targetPosition && intakeMotor.getCurrentEncoderValue() < targetPosition - DEADBAND_VALUE) {
            intakeMotor.set(-motorSpeed, "Buttons said so.");
        }
    }
}
