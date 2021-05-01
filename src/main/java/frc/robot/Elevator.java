package frc.robot;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import frc.robot.buttons.ButtonHandler;
import frc.robot.event.Event;

public class Elevator extends Event{
    private CANSparkMax elevatorMotor;
    private CANPIDController pid;

    public Elevator(CANSparkMax elevatorMotor){
        this.elevatorMotor=elevatorMotor;
        pid=this.elevatorMotor.getPIDController();
        pid.setP(1);
        pid.setI(0);
        pid.setD(0);
        pid.setFF(0);
        pid.setOutputRange(-.3, .2);
    }

    public void setPos(double pos){
        if (pos<-13){
            pos=-13;
        } else if (pos>0){
            pos=0;
        }
        pid.setReference(pos, ControlType.kPosition);
    }
    public double getPos(){
        return elevatorMotor.getEncoder().getPosition();
    }

}