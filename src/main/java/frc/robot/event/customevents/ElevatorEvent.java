package frc.robot.event.customevents;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import frc.robot.Robot;
import frc.robot.buttons.ButtonHandler;
import frc.robot.event.Event;

public class ElevatorEvent extends Event{

    int state=0;
    double pos;
    double deadband=.1;

    public ElevatorEvent(double pos){
        super(0, 10);
        this.pos=pos;
    }
    public void task(){
        switch (state){
            case 0:
                Robot.elevator.setPos(pos);
                state++;
            break;
            case 1:
                if (Robot.elevator.getPos()>pos-deadband && Robot.elevator.getPos()<pos+deadband){
                    state++;
                }
            break;
        }
        
    }

    

    @Override
    public boolean eventCompleteCondition() {
        return state==2;
    }
}