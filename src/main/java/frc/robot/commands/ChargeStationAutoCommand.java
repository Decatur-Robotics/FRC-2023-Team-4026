package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.SetElevatorTargetCommand;
@Deprecated
public class ChargeStationAutoCommand extends CommandBase{
    
    public double drivebackDistance = Constants.chargeStationAutoDriveBackDistance;
    public double driveForwardDistance = Constants.chargeStationAutoDriveForwardDistance;
    public ChargeStationAutoCommand() {

    }

    public void initialize() {

            // .andThen(new AutoBalanceCommand(RobotContainer.drivetrain));

    }
}
