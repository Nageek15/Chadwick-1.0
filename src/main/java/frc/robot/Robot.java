/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.buttons.JLSBAdapter;
import frc.robot.buttons.JRSBAdapter;
import frc.robot.buttons.TSBAdapter;
import frc.robot.event.EventHandler;
import frc.robot.event.core.DriveEventPower;
import frc.robot.event.customevents.ElevatorEvent;
import frc.robot.event.sequence.AutonEventSequence;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static Robot instance;
  public static final EventHandler eHandler=new EventHandler();
  private CANSparkMax lr=new CANSparkMax(10,MotorType.kBrushless);
  private CANSparkMax lf=new CANSparkMax(11,MotorType.kBrushless);
  private CANSparkMax rr=new CANSparkMax(12,MotorType.kBrushless);
  private CANSparkMax rf=new CANSparkMax(13,MotorType.kBrushless);
  public static enum DRIVE_MODE {twoStickArcade,oneStickArcade,tank};
  DRIVE_MODE driveMode=DRIVE_MODE.twoStickArcade;
 //private CANSparkMax itake = new Pro775(14);
  private CANSparkMax itake=new CANSparkMax(14,MotorType.kBrushed);
  private static CANSparkMax pos=new CANSparkMax(15,MotorType.kBrushless);
  private CANSparkMax shoot=new CANSparkMax(16,MotorType.kBrushless);
  public static final Elevator elevator=new Elevator(pos);
  
  public static Servo door = new Servo(0);

  private ADXRS450_Gyro gyro=new ADXRS450_Gyro();

  public CANSparkMax getDriveRight(){
    return rf;
  }
  public CANSparkMax getDriveRearRight(){
    return rr;
  }
  public CANSparkMax getDriveLeft(){
    return lf;
  }
  public CANSparkMax getDriveRearLeft(){
    return lr;
  }


  DifferentialDrive driveControl;
  JLSBAdapter leftJoystick=new JLSBAdapter(new Joystick(0), this);
  JRSBAdapter rightJoystick=new JRSBAdapter(new Joystick(1), this);




  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    instance=this;
    lr.follow(lf);
    rr.follow(rf);
    rr.setIdleMode(IdleMode.kCoast);
    rf.setIdleMode(IdleMode.kCoast);
    lr.setIdleMode(IdleMode.kCoast);
    lf.setIdleMode(IdleMode.kCoast);
    rr.setInverted(false);
    rf.setInverted(false);
    lr.setInverted(false);
    lf.setInverted(false);
    UsbCamera front=CameraServer.getInstance().startAutomaticCapture();
    eHandler.start();
    gyro.calibrate();
  }

  @Override
  public void autonomousInit() {
      driveControl=null;
      gyro.calibrate();
      eHandler.triggerEvent(new AutonEventSequence());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    driveControl = new DifferentialDrive(lf, rf);
    eHandler.clear();
  }
/** */
  @Override
  public void teleopPeriodic() {
    rightJoystick.update();
    leftJoystick.update();
      double rightX=rightJoystick.getX();
      double rightY=rightJoystick.getY()*-1;
      double leftX=leftJoystick.getX();
      double leftY=leftJoystick.getY()*-1;
      boolean slowSpeed=rightJoystick.getButtonDown(3);

      //Deadzones
      if(rightY<.1 && rightY>-.1) {
        rightY=0;
      }
      if(rightX<.1 && rightX>-.1) {
        rightX=0;
      }
      if(leftX<.1 && leftX>-.1) {
        leftX=0;
      }
      if(leftY<.1 && leftY>-.1) {
        leftY=0;
      }
      if(slowSpeed){
        leftY=leftY*.75;
      }
      if(slowSpeed){
        leftX=leftX*.75;
      }
      if(slowSpeed){
        rightY=rightY*.5;
      }
      if(slowSpeed){
        rightX=rightX*.5;
      }
      //Drive Modes
      switch (driveMode){
        case twoStickArcade:
        driveControl.arcadeDrive(rightY, leftX);
        break;
        case oneStickArcade:
        driveControl.arcadeDrive(rightY, rightX);
        break;
        case tank:
        driveControl.tankDrive(leftY, rightY);
        break;
      }
  }

  
  public ADXRS450_Gyro getGyro() {
    return gyro;

  }

  public CANSparkMax getIntake(){
    return itake;
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {}

  public void setDriveMode(DRIVE_MODE d){
    driveMode=d;
  }

    public DRIVE_MODE getDriveMode(){
      return driveMode;
    }
  public static Robot getInstance(){
    return instance;
  }

  public CANSparkMax itake(){
    return itake;
  }
  public CANSparkMax pos(){
    return pos;
  }
  public CANSparkMax shoot(){
    return shoot;
  }
  public Servo door(){
    return door;
  }
  
}
