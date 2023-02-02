package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;

public class WheelIntakeSubsystem extends SubsystemBase {
    
    ITeamTalon wheelIntakeMotorMain;
    ITeamTalon wheelIntakeMotorSub;
    public double motorSpeed = 1;

    public WheelIntakeSubsystem() {
        wheelIntakeMotorMain = new TeamTalonFX("Subsystem.WheelIntake.WheelIntakeMotorMain", Ports.WHEELED_INTAKE_MOTOR_MAIN);
        wheelIntakeMotorSub = new TeamTalonFX("Subsystem.WheelIntke.WheelIntakeMotorSub", Ports.WHEELED_INTAKE_MOTOR_SUB);

        wheelIntakeMotorMain.enableVoltageCompensation(true);
        wheelIntakeMotorSub.enableVoltageCompensation(true);

        wheelIntakeMotorSub.setInverted(true);
        wheelIntakeMotorSub.follow(wheelIntakeMotorMain);

        wheelIntakeMotorMain.setNeutralMode(NeutralMode.Brake);
        wheelIntakeMotorSub.setNeutralMode(NeutralMode.Brake);
    }

    public void RunIntake(String reason) {
        wheelIntakeMotorMain.set(motorSpeed, reason);
    }


    


}
