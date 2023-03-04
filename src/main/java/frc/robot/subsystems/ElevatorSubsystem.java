package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.TeamTalonFX;
import frc.robot.commands.MoveElevatorCommand;

public class ElevatorSubsystem extends SubsystemBase {
    
    public ITeamTalon elevatorMotorMain;
    // public ITeamTalon elevatorMotorSub;
    public double motorSpeed = Constants.elevatorMotorSpeed;
    public double targetPosition;
    public final double DEADBAND_VALUE = Constants.ELEVATOR_DEADBAND_VALUE;

    public Joystick secondaryController;



    public ElevatorSubsystem(Joystick secondary) {
        elevatorMotorMain = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorMain", Ports.ELEVATOR_MOTOR_MAIN);
        // elevatorMotorSub = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorSub", Ports.ELEVATOR_MOTOR_SUB);

        elevatorMotorMain.enableVoltageCompensation(true);
        // elevatorMotorSub.enableVoltageCompensation(true);

        elevatorMotorMain.setNeutralMode(NeutralMode.Brake);
        // elevatorMotorSub.setNeutralMode(NeutralMode.Brake);

        // elevatorMotorSub.follow(elevatorMotorMain);

        secondaryController = secondary;
    }
    public void setTargetPosition(double newTargetPosition, String reason) {
        targetPosition = newTargetPosition;
    }

    public void setSpeed(double speed) {
        if(Math.abs(speed) > 0.05)
            elevatorMotorMain.set(speed, "Joystick said so");
        else elevatorMotorMain.set(0, "Stopping elevator");
        System.out.println(elevatorMotorMain.getCurrentEncoderValue());
    }

    public void periodic() {
        return;
        //double delta = targetPosition - elevatorMotorMain.getCurrentEncoderValue();
        //if (Math.abs(delta)> DEADBAND_VALUE) {
        //    elevatorMotorMain.set(Math.signum(delta)*motorSpeed, "drive to position");
        //} else {
        //    elevatorMotorMain.set(0, "inside deadband");
        //}
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new MoveElevatorCommand(() -> secondaryController.getY(), this));
    }

}
