package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class ClawIntakeSubsystem extends SubsystemBase {
    
    
    Compressor mainCompressor;

    Solenoid clawIntakeSolenoid;

    public double targetPosition;


    public ClawIntakeSubsystem() {
        mainCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
        mainCompressor.enableDigital();

        clawIntakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Ports.CLAW_INTAKE_SOLENOID);

        clawIntakeSolenoid.set(false);
    }

    public void clawTime(boolean clawOpening, String reason) {
        if (clawOpening) {
            clawIntakeSolenoid.set(true);
        }
        else {
            clawIntakeSolenoid.set(false);
        }
    }

    public void periodic() {

    }
}
