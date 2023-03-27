// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    public static final double normalAutoDriveBackDistance = -3.78;
    public static final double chargeStationAutoDriveBackDistance = -4.11;
    public static final double chargeStationAutoDriveForwardDistance = 1.93;
    public static final double backOfGridToFrontOfGridForAutosButNotUsed = 1.42;

    public static final double BALANCE_DISTANCE = -50000.0 * 1.5;
    public static final double OVER_CHARGESTATION_DISTANCE = -50000.0 * 1;
    public static final double RETURN_TO_CHARGESTATION_DISTANCE = 50000 * 1;

    public static final double encoderTicksPerRevolution = 2048;
    public static final double andyMarkHiGripWheelCircumference = 0.1524*Math.PI;
    public static final double motorRotationsPerWheelRotationGearRatio = 10.71;
    public static final double driveDistanceMotorSpeed = 0.25;
    public static final double DRIVEDISTANCE_DEADBANDVALUE = 200;


    public static final double DRIVETRAIN_MAX_POWER_CHANGE = 0.15;
    public static final double ELEVATOR_MAX_POWER_CHANGE = 0.275;

    public static float drivetrainLeftScaler = 0.95f;
    public static float drivetrainRightScaler = 1.16f;
    public static double stopSpeed = 0;

    public static final double ELEVATOR_DEADBAND_VALUE = .6;
    public static final double maxElevatorMotorSpeed = 0.3; //Normally .2

    public static final double MINIMUM_ELEVATOR_POSITION = 2.2;
    public static final double restElevatorTargetPosition = 10.6;
    public static final double carryElevatorPos = 7.05;
    public static final double bottomElevatorTargetPosition = 17.08;
    public static final double middleElevatorTargetPosition = 32.61;
    public static final double topElevatorTargetPosition = 45.5; //48.3;
    public static final double substationPickupElevatorTargetPosition = 31.05;
    public static final double clawCloseThreshold = 11.6;

    public static final double turnDegreesMotorSpeed = 0.1;

    public static final int FINDING_GILL_TAG_WIDTH_DIVISOR = 320;

    public static final int FINDING_GILL_SIDE_LEFT = 0;
    public static final int FINDING_GILL_SIDE_CENTER = 1;
    public static final int FINDING_GILL_SIDE_RIGHT = 2;
    
    public static final int FINDING_GILL_TARGET_LEFT = 0;
    public static final int FINDING_GILL_TARGET_CENTER = 1;
    public static final int FINDING_GILL_TARGET_RIGHT = 2;
    public static final int FINDING_GILL_TARGET_SUBSTATION = 3;

    public static final int AUTO_BALANCE_DEGREES_TO_MOTOR_POWER_DIVISOR = 90;


    public static final double intakeMotorSpeed = 0.25;

    public static final double DRIVE_TRAIN_POWER_EXPONENT = 3;
    public static final double ELEVATOR_POWER_EXPONENT = 3;

    public static final double SLOW_SPEED = 0.25; //CHANGE AFTER ROBOT WEIGHT IS CHANGED!!!
    public static final double NORMAL_SPEED = 0.5; //CHANGE AFTER ROBOT WEIGHT IS CHANGED!!!
    public static final double FAST_SPEED = 0.8; //CHANGE AFTER ROBOT WEIGHT IS CHANGED!!!

    public static final double AUTO_SPEED_MOD = .8;

    public static final double DRIVE_STRAIGHT_DIVISOR = 250;

    public static final double CURRENT_LIMIT_AMPS = 50, CURRENT_TRIGGER_THRESHOLD = 55, CURRENT_LIMIT_TIME = 0.5;
    public static final SupplyCurrentLimitConfiguration CURRENT_LIMIT = 
        new SupplyCurrentLimitConfiguration(true, CURRENT_LIMIT_AMPS, CURRENT_TRIGGER_THRESHOLD, CURRENT_LIMIT_TIME);

    public static final double AUTO_BALANCE_ANGLE_DIVISOR = 15;
    public static final double AUTO_BALANCE_MINIMUM_ANGLE = 15;
    public static final double AUTO_BALANCE_FALL_ANGLE_SIZE = 2;
    public static final double AUTO_BALANCE_MINIMUM_SPEED = 0.15;
    
}
