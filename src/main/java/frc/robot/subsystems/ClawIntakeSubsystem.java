package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class ClawIntakeSubsystem extends SubsystemBase {
    
    
    Compressor mainCompressor;

    public DoubleSolenoid clawGrabber;


    public ClawIntakeSubsystem() {
        mainCompressor = new Compressor(PneumaticsModuleType.CTREPCM);
        clawGrabber = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Ports.CLAW_CLOSE, Ports.CLAW_OPEN);

        clawGrabber.set(Value.kOff);
    }

    public void periodic() {
        
    }
}
