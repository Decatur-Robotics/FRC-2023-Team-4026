package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroPositionSubsystem extends SubsystemBase {
    AnalogGyro gyro;

    public GyroPositionSubsystem(){
        AnalogGyro gyro = new AnalogGyro(0);
    }
}
