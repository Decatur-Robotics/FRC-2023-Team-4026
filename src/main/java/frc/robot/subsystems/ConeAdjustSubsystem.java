package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;

public class ConeAdjustSubsystem extends SubsystemBase {
    
    public ITeamTalon coneAdjustMotor;

    public ConeAdjustSubsystem() {
        coneAdjustMotor = new TeamTalonFX("Subsystem.ConeAdjust.ConeAdjustMotor", Ports.CONE_ADJUST_MOTOR);

        coneAdjustMotor.enableVoltageCompensation(true);

        coneAdjustMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void activateResetMotor(double motorSpeed, String reason) {
        coneAdjustMotor.set(motorSpeed, reason);
    }
}
