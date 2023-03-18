// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SetElevatorTargetCommand;
import frc.robot.commands.AutoBalanceCommand;
import frc.robot.commands.ChargeStationAutoCommand;
import frc.robot.commands.ClawGrabberCommand;
import frc.robot.commands.DriveBackAutoCommand;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.VisionCommand;
import frc.robot.commands.IntakeMotorCommand;
import frc.robot.commands.NormalAutoCommand;
import frc.robot.commands.SpeedModeCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.MoveElevatorCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.ClawIntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static RobotContainer instance;

  // public  FindingGillSubsystem findingGill =  new FindingGillSubsystem();
  public DriveTrainSubsystem drivetrain =  new DriveTrainSubsystem();
  public ClawIntakeSubsystem clawIntake = new ClawIntakeSubsystem();
  public ElevatorSubsystem elevator = new ElevatorSubsystem(clawIntake);
  public VisionSubsystem vision = new VisionSubsystem();

  public static AnalogGyro gyro;

  public Joystick primaryController;
  public Joystick secondaryController;

  public static ShuffleboardTab shuffleboard = Shuffleboard.getTab("SmartDashboard");

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // shuffleboard = Shuffleboard.getTab("SmartDashboard");

    gyro = new AnalogGyro(Ports.GYRO);
    shuffleboard.addDouble("Gyro", ()->gyro.getAngle());

    // findingGill =;
    secondaryController = new Joystick(2); //We need this for testing in elevator

    // Configure the button bindings
    configurePrimaryBindings();
    configureSecondaryBindings();

    addAutoChoicesToGui();

    instance = this;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configurePrimaryBindings() {
    primaryController = new Joystick(1);
    
    drivetrain.setDefaultCommand(new TankDriveCommand(()-> primaryController.getY(), ()-> primaryController.getThrottle(), drivetrain));
    
    // JoystickButton a = new JoystickButton(primaryController,LogitechControllerButtons.a);
    // JoystickButton b = new JoystickButton(primaryController,LogitechControllerButtons.b);
    // JoystickButton x = new JoystickButton(primaryController,LogitechControllerButtons.x);
    // JoystickButton y = new JoystickButton(primaryController,LogitechControllerButtons.y);
    JoystickButton bumperLeft = new JoystickButton(primaryController,LogitechControllerButtons.bumperLeft);
    JoystickButton bumperRight = new JoystickButton(primaryController,LogitechControllerButtons.bumperRight);
    JoystickButton triggerLeft = new JoystickButton(primaryController,LogitechControllerButtons.triggerLeft);
    JoystickButton triggerRight = new JoystickButton(primaryController,LogitechControllerButtons.triggerRight);
    // JoystickButton up = new JoystickButton(primaryController,LogitechControllerButtons.up);
    // JoystickButton down = new JoystickButton(primaryController,LogitechControllerButtons.down);
    // JoystickButton left = new JoystickButton(primaryController,LogitechControllerButtons.left);
    // JoystickButton right = new JoystickButton(primaryController,LogitechControllerButtons.right);
    
    bumperRight.onTrue(new SpeedModeCommand( Constants.FAST_SPEED,drivetrain))
      .onFalse(new SpeedModeCommand(Constants.NORMAL_SPEED, drivetrain));
    triggerLeft.onTrue(new SpeedModeCommand( Constants.SLOW_SPEED,drivetrain))
      .onFalse(new SpeedModeCommand(Constants.NORMAL_SPEED, drivetrain));
    // triggerRight.onTrue(new SpeedModeCommand( Constants.NORMAL_SPEED,drivetrain));

    bumperLeft.onTrue(new DriveStraightCommand(true, drivetrain));
    bumperLeft.onFalse(new DriveStraightCommand(false, drivetrain));

    // triggerLeft.whileTrue(new AutoBalanceCommand(drivetrain));
  }

  private void configureSecondaryBindings() {
    // secondaryController = new Joystick(2);

    elevator.setDefaultCommand(new MoveElevatorCommand(() -> -secondaryController.getY(), elevator));
    
    JoystickButton a = new JoystickButton(secondaryController,LogitechControllerButtons.a);
    JoystickButton b = new JoystickButton(secondaryController,LogitechControllerButtons.b);
    JoystickButton x = new JoystickButton(secondaryController,LogitechControllerButtons.x);
    JoystickButton y = new JoystickButton(secondaryController,LogitechControllerButtons.y);
    JoystickButton bumperLeft = new JoystickButton(secondaryController,LogitechControllerButtons.bumperLeft);
    JoystickButton bumperRight = new JoystickButton(secondaryController,LogitechControllerButtons.bumperRight);
    JoystickButton triggerLeft = new JoystickButton(secondaryController,LogitechControllerButtons.triggerLeft);
    JoystickButton triggerRight = new JoystickButton(secondaryController,LogitechControllerButtons.triggerRight);
    POVButton up = new POVButton(secondaryController,LogitechControllerButtons.up);
    POVButton down = new POVButton(secondaryController,LogitechControllerButtons.down);
    POVButton left = new POVButton(secondaryController,LogitechControllerButtons.left);
    POVButton right = new POVButton(secondaryController,LogitechControllerButtons.right);

    a.whileTrue(new ClawGrabberCommand(Value.kForward,clawIntake));
    b.whileTrue(new ClawGrabberCommand(Value.kReverse,clawIntake));
    // new IntakeMotorCommand( () -> bumperRight.getAsBoolean(),clawIntake);

    up.onTrue(new SetElevatorTargetCommand(Constants.topElevatorTargetPosition, elevator));
    left.onTrue(new SetElevatorTargetCommand(Constants.middleElevatorTargetPosition, elevator));
    right.onTrue(new SetElevatorTargetCommand(Constants.bottomElevatorTargetPosition, elevator));
    down.onTrue(new SetElevatorTargetCommand(Constants.restElevatorTargetPosition, elevator));
    bumperLeft.onTrue(new SetElevatorTargetCommand(Constants.substationPickupElevatorTargetPosition, elevator));
  }

  enum PossibleAutos {
    NORMAL_AUTO,
    CHARGE_STATION_AUTO,
  }


  private final Command normalAuto = new ClawGrabberCommand(Value.kForward, clawIntake)
    .andThen(new SetElevatorTargetCommand( Constants.middleElevatorTargetPosition,elevator))
    .andThen(new ClawGrabberCommand( Value.kReverse,clawIntake))
   .andThen(new DriveDistance(Constants.BALANCE_DISTANCE, drivetrain));

  private final Command chargeStationAuto = new SetElevatorTargetCommand( Constants.topElevatorTargetPosition,elevator)
            .andThen(new ClawGrabberCommand( Value.kForward,clawIntake))
            .andThen(new SetElevatorTargetCommand( Constants.restElevatorTargetPosition,elevator))
            .andThen(new ClawGrabberCommand( Value.kReverse,clawIntake))
            .andThen(new DriveDistance(Constants.BALANCE_DISTANCE, drivetrain))
            .andThen(new DriveDistance(-Constants.BALANCE_DISTANCE, drivetrain));
  private final Command driveBackAuto = new DriveDistance(Constants.BALANCE_DISTANCE, drivetrain);

  private final Command openThenDriveAuto = new ClawGrabberCommand(Value.kReverse, clawIntake)
    .andThen(new DriveDistance(Constants.BALANCE_DISTANCE, drivetrain));
    
  private final Command openClaw = new ClawGrabberCommand(Value.kReverse, clawIntake);

  private final Command overThenBalanceAuto = new ClawGrabberCommand(Value.kReverse, clawIntake)
    .andThen(new DriveDistance(Constants.OVER_CHARGESTATION_DISTANCE, drivetrain))
    .andThen(new DriveDistance(Constants.RETURN_TO_CHARGESTATION_DISTANCE, drivetrain));

  private final Command highBalance = 
    new SetElevatorTargetCommand(Constants.topElevatorTargetPosition, true, elevator)
    .andThen(new ClawGrabberCommand(Value.kReverse, clawIntake))
    .andThen(new SetElevatorTargetCommand(Constants.restElevatorTargetPosition, true, elevator))
    .andThen(new DriveDistance(Constants.OVER_CHARGESTATION_DISTANCE, drivetrain))
    .andThen(new DriveDistance(Constants.RETURN_TO_CHARGESTATION_DISTANCE, drivetrain));
    
  private final Command midBalance = 
    new SetElevatorTargetCommand(Constants.middleElevatorTargetPosition, true, elevator)
    .andThen(new ClawGrabberCommand(Value.kReverse, clawIntake))
    .andThen(new SetElevatorTargetCommand(Constants.restElevatorTargetPosition, true, elevator))
    .andThen(new DriveDistance(Constants.OVER_CHARGESTATION_DISTANCE, drivetrain))
    .andThen(new DriveDistance(Constants.RETURN_TO_CHARGESTATION_DISTANCE, drivetrain));

  private final Command backOut = new DriveDistance(50000 * Constants.normalAutoDriveBackDistance, drivetrain);

  private final Command lowBalance = 
    new SetElevatorTargetCommand(Constants.bottomElevatorTargetPosition, true, elevator)
    .andThen(new ClawGrabberCommand(Value.kReverse, clawIntake))
    .andThen(new SetElevatorTargetCommand(Constants.bottomElevatorTargetPosition, true, elevator))
    .andThen(new DriveDistance(Constants.OVER_CHARGESTATION_DISTANCE, drivetrain))
    .andThen(new DriveDistance(Constants.RETURN_TO_CHARGESTATION_DISTANCE, drivetrain));

  private final Command highBack = 
    new SetElevatorTargetCommand(Constants.topElevatorTargetPosition, true, elevator)
    .andThen(new ClawGrabberCommand(Value.kReverse, clawIntake))
    .andThen(new DriveDistance(50000 * Constants.normalAutoDriveBackDistance, drivetrain));

  private final Command midBack = 
    new SetElevatorTargetCommand(Constants.middleElevatorTargetPosition, true, elevator)
    .andThen(new ClawGrabberCommand(Value.kReverse, clawIntake))
    .andThen(new DriveDistance(50000 * Constants.normalAutoDriveBackDistance, drivetrain));


  private final Command lowBack = 
    new SetElevatorTargetCommand(Constants.bottomElevatorTargetPosition, true, elevator)
    .andThen(new ClawGrabberCommand(Value.kReverse, clawIntake))
    .andThen(new DriveDistance(50000 * Constants.normalAutoDriveBackDistance, drivetrain));


  SendableChooser<Command> autoChooser = new SendableChooser<>();

  private void addAutoChoicesToGui() {
    // PossibleAutos[] enumValues = PossibleAutos.values();
    // for (int i = 0; i < enumValues.length; i++) {
    //   autoChooser.addOption(enumValues[i].toString(), enumValues[i]);
    // }

    autoChooser.setDefaultOption("Open, then Drive Back", openThenDriveAuto);
    autoChooser.addOption("Normal", normalAuto);
    autoChooser.addOption("Charge Station", chargeStationAuto);
    autoChooser.addOption("Drive Back", driveBackAuto);
    autoChooser.addOption("Over, then Balance", overThenBalanceAuto);
    autoChooser.addOption("High Balance", highBalance);
    autoChooser.addOption("High Back Out", highBack);
    autoChooser.addOption("Mid Balance", midBalance);
    autoChooser.addOption("Mid Back Out", midBack);
    autoChooser.addOption("Low Balance", lowBalance);
    autoChooser.addOption("Low Back Out", lowBack);
    shuffleboard.add(autoChooser);

    shuffleboard.add(autoChooser);
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    Command choice = autoChooser.getSelected();
    // choice.schedule();
    return choice;
    // switch (choice) {
    //   case NORMAL_AUTO:
    //     return new NormalAutoCommand();
    //   case CHARGE_STATION_AUTO:
    //     return new ChargeStationAutoCommand();
    //   default:
    //     return null;
    // }
  }
}