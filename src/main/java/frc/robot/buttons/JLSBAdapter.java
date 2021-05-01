package frc.robot.buttons;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.event.customevents.ElevatorEvent;
import frc.robot.event.customevents.PrintEvent;

/**
 * Button handler for right joystick
 */
public class JLSBAdapter extends ButtonHandler{
    private Robot robot;

    public JLSBAdapter(Joystick joystick, Robot robot){
        super(joystick,12);
        this.robot=robot;

    }
    
    public void buttonPressed(int no){
        if (Robot.getInstance().isOperatorControl()){
            switch (no){
                case 1:
                    robot.shoot().set((1-this.getJoystick().getRawAxis(3))/2+0.5);
                break;
                case 2:
                    
                break;
                
                case 3:
                //robot.pos().set(-0.2);
                
                break;
                
                case 4:
                //robot.pos().set(0.1);
                //Robot.elevator.setPos(Robot.elevator.getPos()+.15);
                break; 
                
                case 5:
                Robot.door.setPosition(Robot.door.getPosition()+.33);
                break;
                
                case 6:

                break;
                case 8:
                    /*Robot.eHandler.triggerEvent(new PrintEvent*/System.out.println("Elevator Encoder: "+Robot.elevator.getPos());//);
                break;
                case 11:
                   
                break;
            }  
        } else {
            switch (no){
                case 3:
                    
                break;
                case 4:

                break;
            }
        }
    }

    public void buttonDown(int no){
        switch (no){
            case 3:
                Robot.eHandler.triggerEvent(new ElevatorEvent(-13));
            break;
            case 4:
                Robot.eHandler.triggerEvent(new ElevatorEvent(0));
            break;
        }
    }

    public void buttonReleased(int no){
        switch (no){
            case 1:
                robot.shoot().set(0);
            break;
            case 2:
                
            break;
            case 3:
                //robot.pos().set(0);
            break;
            case 4:
                //robot.pos().set(0);
            break;
            case 5:
                robot.door().set(0);
        }
    }
}