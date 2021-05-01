package frc.robot.buttons;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

/**
 * Button handler for right joystick
 */
public class JRSBAdapter extends ButtonHandler{
    private Robot robot;

    public JRSBAdapter(Joystick joystick, Robot robot){
        super(joystick,12);
        this.robot=robot;

    }
    
    public void buttonPressed(int no){
        if (Robot.getInstance().isOperatorControl()){
            switch (no){
                case 1:
                    robot.itake().set(0.3);
                break;
                case 2:
                    robot.itake().set(-0.3);
                break;
                
                case 3:
                   
                break;
                
                case 4:
                    
                break; 
                
                case 5:

                break;
                
                case 6:

                break;
                case 11:
                    switch (Robot.getInstance().getDriveMode()){
                        case twoStickArcade:
                            Robot.getInstance().setDriveMode(Robot.DRIVE_MODE.oneStickArcade);
                        break;
                        case oneStickArcade:
                            Robot.getInstance().setDriveMode(Robot.DRIVE_MODE.tank);
                        break;
                        case tank:
                            Robot.getInstance().setDriveMode(Robot.DRIVE_MODE.twoStickArcade);
                        break;
                    }
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
    public void buttonReleased(int no){
        switch (no){
            case 1:
                robot.itake().set(0);
            break;
            case 2:
                robot.itake().set(0);
            break;
            case 3:
             
            break;
            case 4:
               
            break;
        
        }
    }
    public void buttonDown(int no){}
}
