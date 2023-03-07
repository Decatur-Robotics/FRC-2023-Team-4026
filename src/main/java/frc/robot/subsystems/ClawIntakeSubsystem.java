package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.TeamSparkMAX;

public class ClawIntakeSubsystem extends SubsystemBase {
    
    
    public Compressor mainCompressor;

    public DoubleSolenoid clawGrabber;

    public TeamSparkMAX intakeMotor;

    public ClawIntakeSubsystem() {
        mainCompressor = new Compressor(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH);
        clawGrabber = new DoubleSolenoid(Ports.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Ports.CLAW_CLOSE, Ports.CLAW_OPEN);

        clawGrabber.set(Value.kOff);

        intakeMotor = new TeamSparkMAX("Subsystems.ClawIntake.IntakeMotor", Ports.INTAKE_MOTOR);

        intakeMotor.enableVoltageCompensation(12);

        mainCompressor.enableDigital();
    }

    public void periodic() {
        
    }
}
