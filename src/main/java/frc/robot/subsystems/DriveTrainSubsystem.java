package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.hal.simulation.AnalogGyroDataJNI;
import edu.wpi.first.wpilibj.AnalogGyro;
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

  
  VisionSubsystem vision = new VisionSubsystem();

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

  public float alignLeftScaler = 1;
  public float alignRightScaler = 1;
  public boolean driveStraight = false;
  public boolean autoAlign = false;

  public double lastInput;

  public AnalogGyro gyro;

  public double currAngle;
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

    //Docs here: https://v5.docs.ctr-electronics.com/en/latest/ch13_MC.html#new-api-in-2020
    SupplyCurrentLimitConfiguration config = new SupplyCurrentLimitConfiguration();
    config.currentLimit = 50;
    config.enable = true;
    config.triggerThresholdCurrent = 55;
    config.triggerThresholdTime = .5;

    leftDriveFalconFront.configSupplyCurrentLimit(config, 250);
    leftDriveFalconBack.configSupplyCurrentLimit(config, 250);
    rightDriveFalconFront.configSupplyCurrentLimit(config, 250);
    rightDriveFalconBack.configSupplyCurrentLimit(config, 250);

    rightDriveFalconFront.resetEncoder();
    leftDriveFalconFront.resetEncoder();
    rightDriveFalconBack.resetEncoder();
    leftDriveFalconBack.resetEncoder();

    // leftDriveFalconFront.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(), 0);

    gyro = RobotContainer.gyro;
    currAngle = gyro.getAngle();
    ShuffleboardTab tab = RobotContainer.shuffleboard;
    tab.addDouble("Speed Mod", ()->speedMod);
    tab.addDouble("Left Drive", ()->leftDriveFalconFront.get());
    tab.addDouble("Right Drive", ()->rightDriveFalconFront.get());
    tab.addDouble("Left Drive Straight", ()->alignLeftScaler);
    tab.addDouble("Right Drive Straight", ()->alignRightScaler);
    tab.addDouble("Left Ticks", ()->leftDriveFalconFront.getCurrentEncoderValue());
    tab.addDouble("Right Ticks", ()->rightDriveFalconFront.getCurrentEncoderValue());
    tab.addDouble("Straight Diff", ()->leftDriveFalconFront.getCurrentEncoderValue()-rightDriveFalconFront.getCurrentEncoderValue());
    tab.addBoolean("Drive Straight", ()->driveStraight);
    tab.addDouble("Vision X", ()->visionX);
    tab.addBoolean("Auto Align", ()->autoAlign);
    tab.addBoolean("Straight Deadband", ()-> -3 < gyro.getAngle() && gyro.getAngle() < 3);
  }

  public void initialize() {
    driveStraight = false;
  }

  // public void setFindingGillSide(double newFindingGillSide) {
  //   findingGillSide = newFindingGillSide;
  // }

  public void setVisionMod(boolean newAutoAlign, double newVisionX) {
    visionX = newVisionX;
    autoAlign = newAutoAlign;
  }

  public void resetEncoders() {
    rightDriveFalconFront.resetEncoder();
    leftDriveFalconFront.resetEncoder();
    rightDriveFalconBack.resetEncoder();
    leftDriveFalconBack.resetEncoder();
  }

  private float[] getAlignmentScalers(double offset, double value, double newMaxOffset) {
    System.out.println("Getting alignment scalers...");

    float[] alignScalers = new float[2];

    double minOffset = 3, maxOffset = newMaxOffset;

    if (Math.abs(offset) > minOffset) {
      double pct = (offset)/maxOffset * -Math.signum(value);
      alignScalers[0] = (float) (1-pct);
      alignScalers[1] = (float) (1+pct);
    }
    else {
      alignScalers[0] = 1;
      alignScalers[1] = 1;
    }

    alignScalers[0] = (float) Math.max(Math.min(alignScalers[0], 2.00), -2.00);
    alignScalers[1] = (float) Math.max(Math.min(alignScalers[1], 2.00), -2.00);

    System.out.println("Returning alignment scalers...");

    return alignScalers;
  }

  private double[] getRampedPower(double desiredLeft, double desiredRight) {
    double currentRightPower = rightDriveFalconFront.get();
    double currentLeftPower = leftDriveFalconFront.get();

    if ((desiredLeft < currentLeftPower))
    {
      desiredLeft = Math.max(desiredLeft, currentLeftPower - Constants.DRIVETRAIN_MAX_POWER_CHANGE);
    } 
    else if ((desiredLeft > currentLeftPower))
    {
      desiredLeft = Math.min(desiredLeft, currentLeftPower + Constants.DRIVETRAIN_MAX_POWER_CHANGE);
    }

    if ((desiredRight < currentRightPower))
    {
      desiredRight = Math.max(desiredRight, currentRightPower - Constants.DRIVETRAIN_MAX_POWER_CHANGE);
    } 
    else if ((desiredRight > currentRightPower))
    {
      desiredRight = Math.min(desiredRight, currentRightPower + Constants.DRIVETRAIN_MAX_POWER_CHANGE);
    }

    double[] desiredPowers = new double[2];

    desiredPowers[0] = desiredLeft;
    desiredPowers[1] = desiredRight;

    return desiredPowers;
  }

  private double getCappedPower(double desired) {
    return Math.min(1, Math.max(-1, desired));
  }

  public void setMotorPowers(double leftPowerDesired, double rightPowerDesired, String reason) 
  {
    if(Math.signum(leftPowerDesired) != Math.signum(lastInput))
      resetEncoders();
    lastInput = leftPowerDesired;

    leftPowerDesired *= Constants.drivetrainLeftScaler;
    rightPowerDesired *= Constants.drivetrainRightScaler;

    if (autoAlign) {
      System.out.println("Auto align running in drivetrain...");

      rightPowerDesired = leftPowerDesired;

      if (vision.coneVisible == 1) {
        System.out.println("Cone seen...");
        visionX = vision.coneX;
      }
      else if (vision.cubeVisible == 1) {
        System.out.println("Cube seen...");
        visionX = vision.cubeX;
      }
      else {
        System.out.println("Nothing seen...");
        visionX = 0;
      }

      float[] alignScalers = getAlignmentScalers(-visionX, leftPowerDesired, 135);
      leftPowerDesired *= alignScalers[0];
      rightPowerDesired *= alignScalers[1];
    }
    else if (driveStraight) {
      rightPowerDesired = leftPowerDesired;

      double gyroOffset; 
      gyroOffset = gyro.getAngle();

      float[] alignScalers = getAlignmentScalers(gyroOffset, leftPowerDesired, 30);
      leftPowerDesired *= alignScalers[0];
      rightPowerDesired *= alignScalers[1];
    }

    leftPowerDesired *= speedMod;
    rightPowerDesired *= speedMod;

    double[] rampedPowers = getRampedPower(leftPowerDesired, rightPowerDesired);

    leftPowerDesired = rampedPowers[0];
    rightPowerDesired = rampedPowers[1];

    leftPowerDesired = getCappedPower(leftPowerDesired);
    rightPowerDesired = getCappedPower(rightPowerDesired);

    // System.out.println("Calculated Powers: L: " + leftPowerDesired
    //   + ", R: " + rightPowerDesired);

    leftDriveFalconFront.set(leftPowerDesired, reason);
    rightDriveFalconFront.set(rightPowerDesired, reason);

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
    currAngle = gyro.getAngle();
    // This method will be called once per scheduler run
  }
}