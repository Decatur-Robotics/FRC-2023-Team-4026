package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;

public class IntakeSubsystem extends SubsystemBase {
    
    
    public ITeamTalon intakeMotor;


    public IntakeSubsystem() {
        intakeMotor = new TeamTalonFX("Subsystem.Intake.IntakeMotor", Ports.INTAKE_MOTOR);

        intakeMotor.enableVoltageCompensation(true);

        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void ActivateIntake(double motorSpeed, String reason) {
        intakeMotor.set(motorSpeed, reason);
    }
}
