package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.ITeamTalon;
import frc.robot.Ports;
import frc.robot.RobotContainer;
import frc.robot.TeamTalonFX;
import frc.robot.commands.MoveElevatorCommand;

public class ElevatorSubsystem extends SubsystemBase {
    
    public ITeamTalon elevatorMotorMain;
    public double motorSpeed = Constants.elevatorMotorSpeed;
    public double targetPosition;
    public final double DEADBAND_VALUE = Constants.ELEVATOR_DEADBAND_VALUE;
    
    public AnalogPotentiometer potentiometer;

    public ElevatorSubsystem()
    {
        elevatorMotorMain = new TeamTalonFX("Subsystem.Elevator.ElevatorMotorMain", Ports.ELEVATOR_MOTOR_MAIN);

        elevatorMotorMain.resetEncoder();
        elevatorMotorMain.enableVoltageCompensation(true);       
        elevatorMotorMain.setInverted(true);
        elevatorMotorMain.setNeutralMode(NeutralMode.Brake);

        potentiometer = new AnalogPotentiometer(Ports.ELEVATOR_POTENTIOMETER, 100);
        RobotContainer.shuffleboard.addDouble("Elevator", () -> potentiometer.get());
        RobotContainer.shuffleboard.addDouble("Elevator Target", () -> targetPosition);
    }

    public void  setTargetPosition(double newTargetPosition, String reason) {
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
        // System.out.println("Current Potentiometer Value: " + potentiometer.get());
        
        // double delta = targetPosition - potentiometer.get();
        // if (Math.abs(delta) > DEADBAND_VALUE) {
        //    elevatorMotorMain.set(Math.signum(delta)*motorSpeed, "moving elevator to position");
        // } else {
        //    elevatorMotorMain.set(0, "elevator inside deadband");
        // }
    }

}
