import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveStraightCommand extends CommandBase {

    public DriveTrainSubsystem drivetrain;
    public AHRS gyro;

    public DriveStraightCommand(DriveTrainSubsystem drivetrains) {
        this.drivetrain = drivetrain;
        gyro = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte) 50);
    }

}