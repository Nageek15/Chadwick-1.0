package frc.robot.event.customevents;

import com.revrobotics.CANSparkMax;

import frc.robot.Robot;
import frc.robot.event.Event;

public class IntakeEvent extends Event {
    boolean on;
    public IntakeEvent(boolean on,long delay){
        super(delay);
        this.on = on;
    }
    public void task(){
        CANSparkMax itake = Robot.getInstance().getIntake();
        if(on){
            itake.set(0.3);
        } else{
            itake.set(0);
        }
    }

}