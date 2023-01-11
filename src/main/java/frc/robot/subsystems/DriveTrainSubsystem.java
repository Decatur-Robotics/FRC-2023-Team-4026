package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.ITeamTalon;
import frc.robot.TeamTalonFX;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
 
  static final double maxPowerChange = 0.1;
  ITeamTalon rightDriveFalconFront;
  ITeamTalon leftDriveFalconFront;
  ITeamTalon rightDriveFalconBack;
  ITeamTalon leftDriveFalconBack;
  public float leftScaler = 1;
  public float RightScaler = 1;


  public DriveTrainSubsystem() {    
    rightDriveFalconFront = new TeamTalonFX("Subsystems.DriveTrain.RightMain", Ports.RIGHT_DRIVE_FALCON_FRONT);
    leftDriveFalconFront = new TeamTalonFX("Subsystems.DriveTrain.LeftMain", Ports.LEFT_DRIVE_FALCON_FRONT);
    rightDriveFalconBack = new TeamTalonFX("Subsystems.DriveTrain.RightSub", Ports.RIGHT_DRIVE_FALCON_BACK);
    leftDriveFalconBack = new TeamTalonFX("Subsystems.DriveTrain.LeftSub", Ports.LEFT_DRIVE_FALCON_BACK);

    leftDriveFalconFront.setInverted(true);
    leftDriveFalconBack.setInverted(true);

    rightDriveFalconBack.follow(rightDriveFalconFront);
    leftDriveFalconBack.follow(leftDriveFalconFront);

    rightDriveFalconFront.setNeutralMode(NeutralMode.Brake);
    leftDriveFalconFront.setNeutralMode(NeutralMode.Brake);
  }
  private double getCappedPower(double desired) {
    return Math.min(1, Math.max(-1, desired));
  }

  public void setMotorPowers(double leftPowerDesired, double rightPowerDesired, String reason) {
    leftPowerDesired = getCappedPower(leftPowerDesired * leftScaler);
    rightPowerDesired = getCappedPower(rightPowerDesired * RightScaler);

    double newPowerRight;
    double newPowerLeft;

    double currentRightPower = rightDriveFalconFront.get();
    double currentLeftPower = leftDriveFalconFront.get();

    if (currentRightPower > rightPowerDesired) {
      newPowerRight = Math.max(currentRightPower - maxPowerChange, rightPowerDesired);
    }
    else if (currentRightPower < rightPowerDesired) {
      newPowerRight = Math.min(currentRightPower + maxPowerChange, rightPowerDesired);
    }
    else {
      newPowerRight = rightPowerDesired;
    }

    if (currentLeftPower > leftPowerDesired) {
      newPowerLeft = Math.max(currentLeftPower - maxPowerChange, leftPowerDesired);
    }
    else if (currentLeftPower < leftPowerDesired) {
      newPowerLeft = Math.min(currentLeftPower + maxPowerChange, leftPowerDesired);
    }
    else {
      newPowerLeft = leftPowerDesired;
    }


    rightDriveFalconFront.set(newPowerRight, reason);
    leftDriveFalconFront.set(newPowerLeft, reason);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}