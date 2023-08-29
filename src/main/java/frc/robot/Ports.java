package frc.robot;

import edu.wpi.first.wpilibj.I2C.Port;

public class Ports {
   public final static int INTAKE_MOTOR = 6;
   public final static int ELEVATOR_MOTOR_MAIN = 18;
   public final static int ELEVATOR_MOTOR_SUB = 17;
   public final static int PNEUMATICS_HUB = 5;
   public final static int CLAW_LEFT_CLOSE = 13; //Do these later
   public final static int CLAW_LEFT_OPEN = 15;
   public final static int CLAW_RIGHT_CLOSE = 0;
   public final static int CLAW_RIGHT_OPEN = 0;
   public final static int ELEVATOR_POTENTIOMETER = 2;
   public final static int ELEVATOR_LIMIT_SWITCH = 9;
   public final static Port NAV_X = Port.kMXP; //Need to check this

   public class SwervePorts { // TODO: Assign port values for each module

      public final static int MOD0_DRIVEMOTOR = 0;
      public final static int MOD0_ANGLEMOTOR = 0;
      public final static int MOD0_CANCODER = 0;

      public final static int MOD1_DRIVEMOTOR = 0;
      public final static int MOD1_ANGLEMOTOR = 0;
      public final static int MOD1_CANCODER = 0;

      public final static int MOD2_DRIVEMOTOR = 0;
      public final static int MOD2_ANGLEMOTOR = 0;
      public final static int MOD2_CANCODER = 0;

      public final static int MOD3_DRIVEMOTOR = 0;
      public final static int MOD3_ANGLEMOTOR = 0;
      public final static int MOD3_CANCODER = 0;

   }
}

