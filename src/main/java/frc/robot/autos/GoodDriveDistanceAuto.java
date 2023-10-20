package frc.robot.autos;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class GoodDriveDistanceAuto extends CommandBase
{
    private double distance;

    SwerveDriveSubsystem s_Swerve;

    private double speed;

    private double targetPosition;

    public GoodDriveDistanceAuto(double distance, double speed, SwerveDriveSubsystem s_Swerve)
    {
        this.distance = distance;
        this.speed = speed;
        this.s_Swerve = s_Swerve;

        targetPosition = s_Swerve.getPose().getX();

        addRequirements(s_Swerve);
    }

    @Override
    public void execute()
    {
        s_Swerve.drive(new Translation2d(speed, 0).times(Constants.Swerve.maxSpeed), 0, /*
                                                                                         * !robotCentricSup
                                                                                         * .
                                                                                         * getAsBoolean
                                                                                         * (),
                                                                                         */ true, // field
                                                                                                  // relative
                                                                                                  // is
                                                                                                  // always
                                                                                                  // on
                true);
    }

    private boolean isInTarget()
    {
        return true;
    }

    @Override
    public boolean isFinished()
    {
        return isInTarget();
    }
}
