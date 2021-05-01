package frc.robot.event.sequence;

import frc.robot.event.CompoundEvent;
import frc.robot.event.Event;
import frc.robot.event.EventSequence;
import frc.robot.event.core.DriveEventPower;
import frc.robot.event.customevents.DriveEvent;
import frc.robot.event.customevents.IntakeEvent;
import frc.robot.event.customevents.TurnEvent;


public class AutonEventSequence extends EventSequence{
    public AutonEventSequence(){
       super(new Event[] {
// ball 1
        new DriveEvent(24.5, 0.2, 100),

        new IntakeEvent(true, 0),
        new IntakeEvent(false, 1000),
// ball 2
        new TurnEvent(.2, 20, 1000),
        new DriveEvent(30, 0.2, 500),

        new IntakeEvent(true, 0),
        new IntakeEvent(false, 1000),
//ball 3
        new TurnEvent(.2, -56.5, 1000),
        new DriveEvent(35.8, 0.3, 500),

        new IntakeEvent(true, 0),
        new IntakeEvent(false, 1000),
// finish 
        new TurnEvent(.2, 22, 1000),
        new DriveEvent(68, 0.3, 500),
        });
    }

}