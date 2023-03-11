package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.ITeamTalon;
import frc.robot.TeamTalonFX;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
 
  static final double MAXPOWERCHANGE = Constants.DRIVETRAIN_MAXPOWERCHANGE;
  public ITeamTalon rightDriveFalconFront;
  public ITeamTalon leftDriveFalconFront;
  ITeamTalon rightDriveFalconBack;
  ITeamTalon leftDriveFalconBack;
  public float leftScaler = Constants.drivetrainLeftScaler;
  public float rightScaler = Constants.drivetrainRightScaler;

  public double tagWidth;
  public double findingGillMod;
  public double findingGillSide;
  public double speedMod = Constants.NORMAL_SPEED;

  public float driveStraightLeftScaler = 1;
  public float driveStraightRightScaler = 1;
  public boolean driveStraight = false;

  public DriveTrainSubsystem() 
  {
    rightDriveFalconFront = new TeamTalonFX("Subsystems.DriveTrain.RightMain", Ports.RIGHT_DRIVE_FALCON_FRONT);
    leftDriveFalconFront = new TeamTalonFX("Subsystems.DriveTrain.LeftMain", Ports.LEFT_DRIVE_FALCON_FRONT);
    rightDriveFalconBack = new TeamTalonFX("Subsystems.DriveTrain.RightSub", Ports.RIGHT_DRIVE_FALCON_BACK);
    leftDriveFalconBack = new TeamTalonFX("Subsystems.DriveTrain.LeftSub", Ports.LEFT_DRIVE_FALCON_BACK);

    rightDriveFalconFront.enableVoltageCompensation(true);
    leftDriveFalconFront.enableVoltageCompensation(true);
    rightDriveFalconBack.enableVoltageCompensation(true);
    leftDriveFalconBack.enableVoltageCompensation(true);

    leftDriveFalconFront.setInverted(true);
    leftDriveFalconBack.setInverted(true);

    rightDriveFalconBack.follow(rightDriveFalconFront);
    leftDriveFalconBack.follow(leftDriveFalconFront);

    rightDriveFalconFront.setNeutralMode(NeutralMode.Brake);
    rightDriveFalconBack.setNeutralMode(NeutralMode.Brake);
    leftDriveFalconFront.setNeutralMode(NeutralMode.Brake);
    leftDriveFalconBack.setNeutralMode(NeutralMode.Brake);

    rightDriveFalconFront.resetEncoder();
    leftDriveFalconFront.resetEncoder();
    rightDriveFalconBack.resetEncoder();
    leftDriveFalconBack.resetEncoder();

    // leftDriveFalconFront.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(), 0);

    ShuffleboardTab tab = RobotContainer.shuffleboard;
    tab.addDouble("Speed Mod", ()->speedMod);
    tab.addDouble("Left Drive", ()->leftDriveFalconFront.get());
    tab.addDouble("Right Drive", ()->rightDriveFalconFront.get());
    tab.addDouble("Left Drive Straight", ()->driveStraightLeftScaler);
    tab.addDouble("Right Drive Straight", ()->driveStraightRightScaler);
    tab.addDouble("Left Ticks", ()->leftDriveFalconFront.getCurrentEncoderValue());
    tab.addDouble("Right Ticks", ()->rightDriveFalconFront.getCurrentEncoderValue());
    tab.addBoolean("Drive Straight", ()->driveStraight);
  }
  
  private double getCappedPower(double desired) 
  {
    return Math.min(1, Math.max(-1, desired));
  }

  public void resetEncoders() {
    rightDriveFalconFront.resetEncoder();
    leftDriveFalconFront.resetEncoder();
    rightDriveFalconBack.resetEncoder();
    leftDriveFalconBack.resetEncoder();
  }

  public void setSpeedMod(double newSpeedMod) {
    speedMod = newSpeedMod;
  }

  public void setFindingGillSide(double newFindingGillSide) {
    findingGillSide = newFindingGillSide;
  }

  public void setFindingGillMod(double newFindingGillMod, double newTagWidth) {
    findingGillMod = newFindingGillMod / 320;
    tagWidth = newTagWidth;

    if (findingGillSide == 0) {
      findingGillMod = findingGillMod - (tagWidth / Constants.FINDING_GILL_TAG_WIDTH_DIVISOR);
    }
    else if (findingGillSide == 2) {
      findingGillMod = findingGillMod + (tagWidth / Constants.FINDING_GILL_TAG_WIDTH_DIVISOR);
    }
  }

  public void setMotorPowers(double leftPowerDesired, double rightPowerDesired, String reason) 
  {
    // leftPowerDesired += findingGillMod;
    // rightPowerDesired -= findingGillMod;

    leftPowerDesired *= speedMod;
    rightPowerDesired *= speedMod;

    if (driveStraight) {
      rightPowerDesired = leftPowerDesired;
    }
    
    leftPowerDesired = getCappedPower(leftPowerDesired * driveStraightLeftScaler);
    rightPowerDesired = getCappedPower(rightPowerDesired * driveStraightRightScaler);

    double newPowerRight = rightPowerDesired;
    double newPowerLeft = leftPowerDesired;

    double currentRightPower = rightDriveFalconFront.get();
    double currentLeftPower = leftDriveFalconFront.get();

    // if ((rightPowerDesired < currentRightPower) && (currentRightPower < 0))
    // {
    //   newPowerRight = Math.max(rightPowerDesired, currentRightPower - MAXPOWERCHANGE);
    // } 
    // else if ((rightPowerDesired > currentRightPower) && (currentRightPower > 0))
    // {
    //   newPowerRight = Math.min(rightPowerDesired, currentRightPower + MAXPOWERCHANGE);
    // } 
    // else 
    // {
    //   newPowerRight = rightPowerDesired;
    // }

    // if ((leftPowerDesired < currentLeftPower) && (currentLeftPower < 0))
    // {
    //   newPowerLeft = Math.max(leftPowerDesired, currentLeftPower - MAXPOWERCHANGE);
    // } 
    // else if ((leftPowerDesired > currentLeftPower) && (currentLeftPower > 0))
    // {
    //   newPowerLeft = Math.min(leftPowerDesired, currentLeftPower + MAXPOWERCHANGE);
    // } 
    // else 
    // {
    //   newPowerLeft = leftPowerDesired;
    // }

    if (speedMod == Constants.SLOW_SPEED) {
      newPowerLeft *= 1 / Constants.SLOW_SPEED;
      newPowerRight *= 1 / Constants.SLOW_SPEED;
    }

    if(speedMod == Constants.NORMAL_SPEED) {
      newPowerLeft *= 1 / Constants.NORMAL_SPEED;
      newPowerRight *= 1 / Constants.NORMAL_SPEED;
    }

    newPowerLeft = Math.signum(newPowerLeft) * Math.pow(newPowerLeft, Constants.DRIVE_TRAIN_POWER_EXPONENT);
    newPowerRight = Math.signum(newPowerRight) * Math.pow(newPowerRight, Constants.DRIVE_TRAIN_POWER_EXPONENT);
    
    if (Math.abs(newPowerLeft) >= 0.01)
      newPowerLeft = Math.signum(newPowerLeft) * Math.max(Math.abs(newPowerLeft), 0.1);
    if(Math.abs(newPowerRight) >= 0.01)
      newPowerRight = Math.signum(newPowerRight) * Math.max(Math.abs(newPowerRight), 0.1);

    newPowerLeft *= leftScaler;
    newPowerRight *= rightScaler;

    if (speedMod == Constants.SLOW_SPEED) {
      newPowerLeft /= 1 / Constants.SLOW_SPEED;;
      newPowerRight /= 1 / Constants.SLOW_SPEED;;
    }

    if(speedMod == Constants.NORMAL_SPEED) {
      newPowerLeft /= 1 / Constants.NORMAL_SPEED;;
      newPowerRight /= 1 / Constants.NORMAL_SPEED;;
    }

    // System.out.println("Calculated Powers: L: " + newPowerLeft
    //   + ", R: " + newPowerRight);

    leftDriveFalconFront.set(newPowerLeft, reason);
    rightDriveFalconFront.set(newPowerRight, reason);

    // System.out.println("Set motor powers: LF: " + leftDriveFalconFront.get()
    //   + ", LB: " + leftDriveFalconBack.get()
    //   + ", RF: " + rightDriveFalconFront.get()
    //   + ", RB: " + rightDriveFalconBack.get());
  }

  public void setDirect(double left, double right, String reason) {
    leftDriveFalconFront.set(left * leftScaler);
    rightDriveFalconFront.set(right * rightScaler);

    // System.out.println("Set motor powers directly: " + leftDriveFalconFront.get()
    //    + ", " + rightDriveFalconFront.get());
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}