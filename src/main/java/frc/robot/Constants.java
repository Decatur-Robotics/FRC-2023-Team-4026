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
    public static double chargeStationAutoDriveBackDistance = -4.11;
    public static double chargeStationAutoDriveForwardDistance = 1.93;
    public static double backOfGridToFrontOfGridForAutosButNotUsed = 1.42;

    public static double encoderTicksPerRevolution = 2048;
    public static double andyMarkHiGripWheelCircumference = 0.1524*Math.PI;
    public static double motorRotationsPerWheelRotationGearRatio = 10.71;
    public static double driveDistanceMotorSpeed = 0.25;
    public static final double DRIVEDISTANCE_DEADBANDVALUE = 200;


    public static final double DRIVETRAIN_MAXPOWERCHANGE = 0.1;
    public static float drivetrainLeftScaler = 1f;
    public static float drivetrainRightScaler = 1.225f;
    public static double stopSpeed = 0;


    public static final double ELEVATOR_DEADBAND_VALUE = 10;
    public static double elevatorMotorSpeed = 0.2;

    public static long restElevatorTargetPosition = 0;
    public static long bottomElevatorTargetPosition = 25;
    public static long middleElevatorTargetPosition = 50;
    public static long topElevatorTargetPosition = 75; 
    public static long substationPickupElevatorTargetPosition = 100;

    public static double turnDegreesMotorSpeed = 0.1;

    public static final int FINDING_GILL_TAG_WIDTH_DIVISOR = 320;

    public static final int FINDING_GILL_SIDE_LEFT = 0;
    public static final int FINDING_GILL_SIDE_CENTER = 1;
    public static final int FINDING_GILL_SIDE_RIGHT = 2;
    
    public static final int FINDING_GILL_TARGET_LEFT = 0;
    public static final int FINDING_GILL_TARGET_CENTER = 1;
    public static final int FINDING_GILL_TARGET_RIGHT = 2;
    public static final int FINDING_GILL_TARGET_SUBSTATION = 3;

    public static final int AUTO_BALANCE_DEGREES_TO_MOTOR_POWER_DIVISOR = 90;


    public static double intakeMotorSpeed = 0.25;

    public static double DRIVE_TRAIN_POWER_EXPONENT = 2;

    public static double NORMAL_SPEED = 0.5;
    public static double FAST_SPEED = 1;

}
