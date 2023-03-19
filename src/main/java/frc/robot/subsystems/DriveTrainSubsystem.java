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
  public double visionX = 0;
  public double findingGillSide;
  public double speedMod = Constants.NORMAL_SPEED;

  public float driveStraightLeftScaler = 1;
  public float driveStraightRightScaler = 1;
  public boolean driveStraight = false;
  public boolean autoAlign = false;

  public double lastInput;

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

    SupplyCurrentLimitConfiguration config = new SupplyCurrentLimitConfiguration();
    config.currentLimit = 55;
    leftDriveFalconFront.configSupplyCurrentLimit(config, 250);
    leftDriveFalconBack.configSupplyCurrentLimit(config, 250);
    rightDriveFalconFront.configSupplyCurrentLimit(config, 250);
    rightDriveFalconBack.configSupplyCurrentLimit(config, 250);

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
    tab.addDouble("Straight Diff", ()->leftDriveFalconFront.getCurrentEncoderValue()-rightDriveFalconFront.getCurrentEncoderValue());
    tab.addBoolean("Drive Straight", ()->driveStraight);
    tab.addDouble("Vision X", ()->visionX);
    tab.addBoolean("Auto Align", ()->autoAlign);
  }

  public void initialize() {
    driveStraight = false;
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

  // public void setFindingGillSide(double newFindingGillSide) {
  //   findingGillSide = newFindingGillSide;
  // }

  public void setVisionMod(boolean newAutoAlign, double newVisionX) {
    visionX = newVisionX;
    autoAlign = newAutoAlign;
  }

  public void setMotorPowers(double leftPowerDesired, double rightPowerDesired, String reason) 
  {
    if(Math.signum(leftPowerDesired) != Math.signum(lastInput))
      resetEncoders();
    lastInput = leftPowerDesired;

    leftPowerDesired *= speedMod;
    rightPowerDesired *= speedMod;

    if (driveStraight) {
      rightPowerDesired = leftPowerDesired;
      
      double left = Math.abs(leftDriveFalconFront.getCurrentEncoderValue()), 
      right = Math.abs(rightDriveFalconFront.getCurrentEncoderValue());

      float divisor = 10000;

      if(left < right) driveStraightLeftScaler = 1f + (float)(right-left)/divisor;
      else driveStraightLeftScaler = 1;
          
      if(right < left) driveStraightRightScaler = 1f + (float)(left-right)/divisor;
      else driveStraightRightScaler = 1;

      driveStraightLeftScaler = (float) Math.min(driveStraightLeftScaler, 1.2);
      driveStraightRightScaler = (float) Math.min(driveStraightRightScaler, 1.2);
    }

    if (autoAlign) {
      rightPowerDesired = leftPowerDesired;

      if (visionX > 0) {
        leftPowerDesired += visionX/100;
      }
      else if (visionX < 0) {
        rightPowerDesired += visionX/100;
      }
    }
    
    leftPowerDesired = getCappedPower(leftPowerDesired * driveStraightLeftScaler);
    rightPowerDesired = getCappedPower(rightPowerDesired * driveStraightRightScaler);

    double newPowerRight = rightPowerDesired;
    double newPowerLeft = leftPowerDesired;

    double currentRightPower = rightDriveFalconFront.get();
    double currentLeftPower = leftDriveFalconFront.get();

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

    if ((rightPowerDesired < currentRightPower))
    {
      newPowerRight = Math.max(rightPowerDesired, currentRightPower - MAXPOWERCHANGE);
    } 
    else if ((rightPowerDesired > currentRightPower))
    {
      newPowerRight = Math.min(rightPowerDesired, currentRightPower + MAXPOWERCHANGE);
    } 
    else 
    {
      newPowerRight = rightPowerDesired;
    }

    if ((leftPowerDesired < currentLeftPower))
    {
      newPowerLeft = Math.max(leftPowerDesired, currentLeftPower - MAXPOWERCHANGE);
    } 
    else if ((leftPowerDesired > currentLeftPower))
    {
      newPowerLeft = Math.min(leftPowerDesired, currentLeftPower + MAXPOWERCHANGE);
    } 
    else 
    {
      newPowerLeft = leftPowerDesired;
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