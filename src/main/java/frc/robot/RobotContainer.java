// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.RetractedElevatorCommand;
import frc.robot.commands.AutoBalanceCommand;
import frc.robot.commands.BottomElevatorCommand;
import frc.robot.commands.ChargeStationAutoCommand;
import frc.robot.commands.ClawGrabberCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FindingGillCommand;
import frc.robot.commands.HighElevatorCommand;
import frc.robot.commands.MiddleElevatorCommand;
import frc.robot.commands.NormalAutoCommand;
import frc.robot.commands.SetFindingGillSideCommand;
import frc.robot.commands.SpeedModeCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FindingGillSubsystem;
import frc.robot.subsystems.ClawIntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static FindingGillSubsystem findingGill;
  public static DriveTrainSubsystem drivetrain;
  public static ClawIntakeSubsystem clawIntake;
  public static ElevatorSubsystem elevator;

  public Joystick primaryController;
  public Joystick secondaryController;

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    findingGill = new FindingGillSubsystem();
    drivetrain = new DriveTrainSubsystem();
    clawIntake = new ClawIntakeSubsystem();
    elevator = new ElevatorSubsystem();

    // Configure the button bindings
    configurePrimaryBindings();
    configureSecondaryBindings();

    addAutoChoicesToGui();
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
    
    JoystickButton a = new JoystickButton(primaryController,LogitechControllerButtons.a);
    JoystickButton b = new JoystickButton(primaryController,LogitechControllerButtons.b);
    JoystickButton x = new JoystickButton(primaryController,LogitechControllerButtons.x);
    JoystickButton y = new JoystickButton(primaryController,LogitechControllerButtons.y);
    JoystickButton bumperLeft = new JoystickButton(primaryController,LogitechControllerButtons.bumperLeft);
    JoystickButton bumperRight = new JoystickButton(primaryController,LogitechControllerButtons.bumperRight);
    JoystickButton triggerLeft = new JoystickButton(primaryController,LogitechControllerButtons.triggerLeft);
    JoystickButton triggerRight = new JoystickButton(primaryController,LogitechControllerButtons.triggerRight);
    JoystickButton up = new JoystickButton(primaryController,LogitechControllerButtons.up);
    JoystickButton down = new JoystickButton(primaryController,LogitechControllerButtons.down);
    JoystickButton left = new JoystickButton(primaryController,LogitechControllerButtons.left);
    JoystickButton right = new JoystickButton(primaryController,LogitechControllerButtons.right);

    x.whileTrue(new FindingGillCommand(findingGill, drivetrain, Constants.FINDING_GILL_TARGET_LEFT));
    y.whileTrue(new FindingGillCommand(findingGill, drivetrain, Constants.FINDING_GILL_TARGET_CENTER));
    a.whileTrue(new FindingGillCommand(findingGill, drivetrain, Constants.FINDING_GILL_TARGET_RIGHT));
    b.whileTrue(new FindingGillCommand(findingGill, drivetrain, Constants.FINDING_GILL_TARGET_SUBSTATION));

    bumperLeft.whileTrue(new SetFindingGillSideCommand(drivetrain, Constants.FINDING_GILL_SIDE_LEFT));
    bumperRight.whileTrue(new SetFindingGillSideCommand(drivetrain, Constants.FINDING_GILL_SIDE_RIGHT));

    triggerRight.whileTrue(new SpeedModeCommand(drivetrain));

    triggerLeft.whileTrue(new AutoBalanceCommand(drivetrain));
  }

  private void configureSecondaryBindings() {
    secondaryController = new Joystick(2);
    
    JoystickButton a = new JoystickButton(secondaryController,LogitechControllerButtons.a);
    JoystickButton b = new JoystickButton(secondaryController,LogitechControllerButtons.b);
    JoystickButton x = new JoystickButton(secondaryController,LogitechControllerButtons.x);
    JoystickButton y = new JoystickButton(secondaryController,LogitechControllerButtons.y);
    JoystickButton bumperLeft = new JoystickButton(secondaryController,LogitechControllerButtons.bumperLeft);
    JoystickButton bumperRight = new JoystickButton(secondaryController,LogitechControllerButtons.bumperRight);
    JoystickButton triggerLeft = new JoystickButton(secondaryController,LogitechControllerButtons.triggerLeft);
    JoystickButton triggerRight = new JoystickButton(secondaryController,LogitechControllerButtons.triggerRight);
    JoystickButton up = new JoystickButton(secondaryController,LogitechControllerButtons.up);
    JoystickButton down = new JoystickButton(secondaryController,LogitechControllerButtons.down);
    JoystickButton left = new JoystickButton(secondaryController,LogitechControllerButtons.left);
    JoystickButton right = new JoystickButton(secondaryController,LogitechControllerButtons.right);

    a.onTrue(new ClawGrabberCommand(clawIntake, Value.kForward));
    b.onTrue(new ClawGrabberCommand(clawIntake, Value.kReverse));

    up.onTrue(new HighElevatorCommand(elevator));
    left.onTrue(new MiddleElevatorCommand(elevator));
    right.onTrue(new BottomElevatorCommand(elevator));
    down.onTrue(new RetractedElevatorCommand(elevator));



  }

  enum PossibleAutos {
    TWO_BALL_AUTO,
    ONE_BALL_AUTO,
  }


  private final Command normalAuto = new NormalAutoCommand();
  private final Command chargeStationAuto = new ChargeStationAutoCommand();

  SendableChooser<PossibleAutos> autoChooser = new SendableChooser<PossibleAutos>();

  private void addAutoChoicesToGui() {
    PossibleAutos[] enumValues = PossibleAutos.values();
    for (int i = 0; i < enumValues.length; i++) {
      autoChooser.addOption(enumValues[i].toString(), enumValues[i]);
    }
    SmartDashboard.putData(autoChooser);
  }
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    PossibleAutos choice = autoChooser.getSelected();
    switch (choice) {
      case TWO_BALL_AUTO:
        return normalAuto;
      case ONE_BALL_AUTO:
        return chargeStationAuto;
      default:
        return null;
    }
  }
}