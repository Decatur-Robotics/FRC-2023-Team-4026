package frc.robot.autos;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AutoBalanceAuto extends CommandBase {
    SwerveDriveSubsystem s_Swerve;

    private double balancingSpeed;
    private int balancingDirection;

    private double tiltDeadband;

    private double wheelLockSpeed;

    private boolean wheelsLocked;

    private AnalogGyro gyro;

    public AutoBalanceAuto(SwerveDriveSubsystem s_Swerve) {
        this.s_Swerve = s_Swerve;
        balancingSpeed = 0.25;
        balancingDirection = 1;
        tiltDeadband = 10;
        wheelLockSpeed = 0;

        wheelsLocked = false;

        gyro = s_Swerve.pitchGyro;

        addRequirements(s_Swerve);
    }

    @Override
    public void execute() {
        if (gyro.getAngle() < tiltDeadband && gyro.getAngle() > -tiltDeadband) {
            balancingDirection = 0;

            if (!wheelsLocked) {
                wheelLockSpeed = 0.01;
                wheelsLocked = true;
            }
        } else if (gyro.getAngle() > 0 && balancingDirection == 1) {
            balancingSpeed /= 2;
            balancingDirection = -1;
            wheelLockSpeed = 0;
            wheelsLocked = false;
        } else if (gyro.getAngle() < 0 && balancingDirection == -1) {
            balancingSpeed /= 2;
            balancingDirection = 1;
            wheelLockSpeed = 0;
            wheelsLocked = false;
        }

        if (balancingSpeed < 0.05)
            balancingSpeed = 0.05;

        s_Swerve.drive(
                new Translation2d(balancingSpeed * balancingDirection, wheelLockSpeed)
                        .times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                /* !robotCentricSup.getAsBoolean(), */ true, // field relative is always on
                true);

        wheelLockSpeed = 0;
    }

}
