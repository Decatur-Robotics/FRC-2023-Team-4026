// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ConeAdjustCommand;
import frc.robot.commands.ConeIntakeCommand;
import frc.robot.commands.CubeIntakeCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.OpenIntakeCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.ConeAdjustSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PositioningSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public DriveTrainSubsystem drivetrain;
  public PositioningSubsystem positioning;
  public IntakeSubsystem intake;
  public ConeAdjustSubsystem coneAdjust;

  public Joystick primaryController;
  public Joystick secondaryController;

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    drivetrain = new DriveTrainSubsystem();
    positioning = new PositioningSubsystem();
    intake = new IntakeSubsystem();
    coneAdjust = new ConeAdjustSubsystem();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    primaryController = new Joystick(0);
    drivetrain.setDefaultCommand(new TankDriveCommand(()-> primaryController.getY(), ()-> primaryController.getThrottle(), drivetrain));

    secondaryController = new Joystick(1);
    JoystickButton x = new JoystickButton(secondaryController,LogitechControllerButtons.x);
    JoystickButton a = new JoystickButton(secondaryController,LogitechControllerButtons.a);
    JoystickButton b = new JoystickButton(secondaryController,LogitechControllerButtons.b);
    JoystickButton y = new JoystickButton(secondaryController, LogitechControllerButtons.y);

    x.onTrue(new OpenIntakeCommand(intake));
    a.onTrue(new ConeIntakeCommand(intake));
    b.onTrue(new CubeIntakeCommand(intake));
    y.onTrue(new ConeAdjustCommand(coneAdjust));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}