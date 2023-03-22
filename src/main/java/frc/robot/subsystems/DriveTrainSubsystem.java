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

    gyro = RobotContainer.gyro;

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

  public void resetEncoders() {
    rightDriveFalconFront.resetEncoder();
    leftDriveFalconFront.resetEncoder();
    rightDriveFalconBack.resetEncoder();
    leftDriveFalconBack.resetEncoder();
  }

  private float[] getAlignmentScalers(double offset, double value) {
    float[] alignScalers = new float[2];

    double minOffset = 3, maxOffset = 90;

    if (Math.abs(offset) > minOffset) {
      double pct = (offset)/maxOffset * -Math.signum(value);
      alignScalers[0] = (float) (1-pct);
      alignScalers[1] = (float) (1+pct);
    }
    else {
      alignScalers[0] = 1;
      alignScalers[1] = 1;
    }

    alignScalers[0] = (float) Math.max(Math.min(alignScalers[0], 1.2), -1.2);
    alignScalers[1] = (float) Math.max(Math.min(alignScalers[1], 1.2), -1.2);

    return alignScalers;
  }

  private double getRampedPower(double desired) {
    double currentRightPower = rightDriveFalconFront.get();
    double currentLeftPower = leftDriveFalconFront.get();

    if ((desired < currentRightPower))
    {
      desired = Math.max(desired, currentRightPower - Constants.DRIVETRAIN_MAX_POWER_CHANGE);
    } 
    else if ((desired > currentRightPower))
    {
      desired = Math.min(desired, currentRightPower + Constants.DRIVETRAIN_MAX_POWER_CHANGE);
    }

    return desired;
  }

  private double getCappedPower(double desired) {
    return Math.min(1, Math.max(-1, desired));
  }

  public void setMotorPowers(double leftPowerDesired, double rightPowerDesired, String reason) 
  {
    if(Math.signum(leftPowerDesired) != Math.signum(lastInput))
      resetEncoders();
    lastInput = leftPowerDesired;

    leftPowerDesired *= speedMod;
    rightPowerDesired *= speedMod;

    leftPowerDesired *= Constants.drivetrainLeftScaler;
    rightPowerDesired *= Constants.drivetrainRightScaler;

    if (autoAlign) {
      float[] alignScalers = getAlignmentScalers(visionX, leftPowerDesired);
      alignLeftScaler = alignScalers[0];
      alignRightScaler = alignScalers[1];
    }
    else if (driveStraight) {
      float[] alignScalers = getAlignmentScalers(gyro.getAngle(), leftPowerDesired);
      alignLeftScaler = alignScalers[0];
      alignRightScaler = alignScalers[1];
    }

    leftPowerDesired = getRampedPower(leftPowerDesired);
    rightPowerDesired = getRampedPower(rightPowerDesired);

    // System.out.println("Calculated Powers: L: " + leftPowerDesired
    //   + ", R: " + rightPowerDesired);
    
    leftPowerDesired = getCappedPower(leftPowerDesired * alignLeftScaler);
    rightPowerDesired = getCappedPower(rightPowerDesired * alignRightScaler);

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
    // This method will be called once per scheduler run
  }
}