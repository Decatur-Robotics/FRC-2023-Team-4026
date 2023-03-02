// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    public static double normalAutoDriveBackDistance = -3.78;
    public static double chargeStationAutoDriveBackDistance = -2.18;
    public static double backOfGridToFrontOfGridForAutosButNotUsed = 1.42;

    public static double encoderTicksPerRevolution = 2048;
    public static double andyMarkHiGripWheelCircumference = 0.1524*Math.PI;
    public static double motorRotationsPerWheelRotationGearRatio = 10.71;
    public static double driveDistanceMotorSpeed = 0.5;
    public static final double DRIVEDISTANCE_DEADBANDVALUE = 200;


    public static final double DRIVETRAIN_MAXPOWERCHANGE = 0.1;
    public static float drivetrainLeftScaler = 1;
    public static float drivetrainRightScaler = 1;
    public static double stopSpeed = 0;


    public static final double ELEVATOR_DEADBAND_VALUE = 10;
    public static double elevatorMotorSpeed = 1;

    public static long retractedElevatorTargetPosition = 0;
    public static long bottomElevatorTargetPositionv = 5;
    public static long middleElevatorTargetPosition = 10;
    public static long highElevatorTargetPosition = 15; 


    public static double turnDegreesMotorSpeed = 0.5;


    



}
