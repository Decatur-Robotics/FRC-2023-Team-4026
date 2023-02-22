package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PositioningSubsystem extends SubsystemBase {

    public static double[] pos = new double[]{ -1, -1, -1 };
    public static double rot = -1;

    public PositioningSubsystem() {

    }

    public void periodic() {
        pos = SmartDashboard.getNumberArray("pos", new double[]{ -1, -1, -1 });
        rot = SmartDashboard.getNumber("rot", -1);
    }
    
}
