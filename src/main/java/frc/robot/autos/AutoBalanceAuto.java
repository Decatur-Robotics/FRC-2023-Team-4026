package frc.robot.autos;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AutoBalanceAuto extends CommandBase 
{
    SwerveDriveSubsystem s_Swerve;

    private double balancingSpeed;
    private int balancingDirection;

    private double tiltDeadband;

    private AHRS gyro;

    public AutoBalanceAuto(SwerveDriveSubsystem s_Swerve) 
    {
        this.s_Swerve = s_Swerve;
        balancingSpeed = 0.5;
        balancingDirection = 1;
        tiltDeadband = 10;

        gyro = new AHRS(Port.kMXP);

        addRequirements(s_Swerve);
    }

    @Override
    public void execute() 
    {
        if (gyro.getRoll() < tiltDeadband && gyro.getRoll() > -tiltDeadband) 
        {
            balancingDirection = 0;
        }
        else if (gyro.getRoll() > 0 && balancingDirection == 1) 
        {
            balancingSpeed /= 2;
            balancingDirection = -1;
        }
        else if (gyro.getRoll() < 0 && balancingDirection == -1) 
        {
            balancingSpeed /= 2;
            balancingDirection = 1;
        }

        if (balancingSpeed < 0.1) balancingSpeed = 0.1;

        s_Swerve.drive(new Translation2d(balancingSpeed * balancingDirection, 0).times(
            Constants.Swerve.maxSpeed), 0 * Constants.Swerve.maxAngularVelocity,
            /* !robotCentricSup.getAsBoolean(), */ true, // field relative is always on
            true);
    }


    
}
