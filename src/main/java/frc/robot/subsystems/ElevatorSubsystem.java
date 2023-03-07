package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
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
    
    public AnalogPotentiometer potentiometer;

    public ElevatorSubsystem()
    {
        elevatorMotorMain = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorMain", Ports.ELEVATOR_MOTOR_MAIN);
        // elevatorMotorSub = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorSub", Ports.ELEVATOR_MOTOR_SUB);

        elevatorMotorMain.resetEncoder();

        elevatorMotorMain.enableVoltageCompensation(true);
        // elevatorMotorSub.enableVoltageCompensation(true);

        

        elevatorMotorMain.setInverted(true);
        // elevatorMotorSub.setNeutralMode(NeutralMode.Brake);

        elevatorMotorMain.setNeutralMode(NeutralMode.Brake);

        // elevatorMotorSub.follow(elevatorMotorMain);

        // potentiometer = new AnalogPotentiometer(Ports.ELEVATOR_POTENTIOMETER, 100);
    }

    public void setTargetPosition(double newTargetPosition, String reason) {
        targetPosition = newTargetPosition;
    }

    public void setSpeed(double speed) {
        if(Math.abs(speed) > 0.5)
            elevatorMotorMain.set(Constants.elevatorMotorSpeed * Math.signum(speed), "Joystick said so");
        else {
            elevatorMotorMain.set(0, "Stopping elevator");
        }
        
    }
        

    public void periodic() {
        // System.out.println("Elevator Power: " + elevatorMotorMain.get());
        // System.out.println("Current Elevator Encoder Value: " + potentiometer.get());
        return;
        //double delta = targetPosition - potentiometer.get();
        //if (Math.abs(delta)> DEADBAND_VALUE) {
        //    elevatorMotorMain.set(Math.signum(delta)*motorSpeed, "drive to position");
        //} else {
        //    elevatorMotorMain.set(0, "inside deadband");
        //}
    }
    
    // public void initDefaultCommand() {
    //     setDefaultCommand(new MoveElevatorCommand(() -> secondaryController.getY(), this));
    // }

}
