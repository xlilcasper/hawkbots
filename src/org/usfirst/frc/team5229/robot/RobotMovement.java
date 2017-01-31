package org.usfirst.frc.team5229.robot;

import java.util.Scanner;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotMovement {
	private boolean modeArcade = true; 
	private boolean modeFine = true; 
    private boolean squaredInputs = true; 
    private static double wheelBase = 24.0;
    
    
    private Sensors sensors = new Sensors();
   
	//a constructor that doesn't take an input
	public RobotMovement(){
	}
	
	//in: newWheelBase
	//out:nothing
	public RobotMovement(double newWheelBase){
		wheelBase = newWheelBase;
		//wheelBase is the distance from the middle of the right wheel to the middle of the left wheel
	}
	
	//in:Joystick Left, Joystick Right
	//out: nothing
	public RobotMovement(Joystick Left, Joystick Right){
		//set left joystick set right joystick
		SetLeftJoystick(Left);
		SetRightJoystick(Right);
	}
	
    //in:Joystick Left, Joystick Right, newWheelBase
	//out:nothing
	public RobotMovement(Joystick Left, Joystick Right, double newWheelBase){
		//set left joystick set right joystick
		SetLeftJoystick(Left);
		SetRightJoystick(Right);
		wheelBase = newWheelBase;
	}

	RobotDrive myRobot = new RobotDrive(0 , 1, 2, 3); // why is there 0, 1, 2, 3? What are those?
	Joystick leftStick;
	Joystick rightStick;
	
    Scanner in = new Scanner(System.in);
	
	
    //set left stick//
    //in:Joystick lJoystick
    //out:nothing
    public void SetLeftJoystick(Joystick lJoystick){
    	leftStick = lJoystick;

    }
    
    //set right stick//
    //in:Joystick rJoystick
    //out:nothing
    public void SetRightJoystick(Joystick rJoystick) {
    	rightStick = rJoystick;
    }
	
    //in:newWheelBase
    //out:nothing
   public void createWheelBase(double newWheelBase){
	wheelBase = newWheelBase;
		//wheelBase is the distance from the middle of the right wheel to the middle of the left wheel
	} 
    //set drive forward and backward//
    //in: speed
    //out: nothing
    //speed>0, moves forward
    //speed<0, moves backward
    public void DrivefowardBackward(double speed){
    	if(speed>1.0){
    		speed=1;
    	}
    	if(speed<-1.0){
    		speed=-1;
    	}
    	myRobot.drive(speed,0);
    	
    }
    
    //in:r
    //out:Math.exp(-r/wheelBase)
    private double rToCurve(double r){
    	return Math.exp(-r/wheelBase);
	//takes the the wheelBase of a robot and the radius of the circle that the curve would be part of and imputs
	//it into a function that outputs the the curve 
    }
    
  //how far the outside wheel(right wheel) is going
    //in:r,angdeg
    //out:(r+wheelBase/2)*Math.toradians(angdeg)
    public double angleToTurnDistance(double r, double angdeg){
	   return (r+wheelBase/2)*Math.toRadians(angdeg);
    }
    
    //turn left
    //in: speed, radius(r)
    //out: nothing
    public void turnLeft(double speed, double r){
    	myRobot.drive(speed, -rToCurve(r));
    }
    
  //turn right
    //in: speed, radius(r)
    //out: nothing
    public void turnRight(double speed, double r){
    	myRobot.drive(speed, rToCurve(r));
    }
    
    //tell what the is speed//
    //in:speed
    //out:speed
    public int whatisSpeed(int speed){
    	return speed;
    }
    
    //tell how many degrees did the robot turn//
    //in:r
    //out:rToCurve(r)
    public double whatisDegree(double r){
    	return rToCurve(r);
    }
    
    //set mode to arcadeDrive//
    //in:nothing
    //out:nothing
	public void setmodeArcade(){
		modeArcade = true; 
	}
	
	//set mode to tankDrive//
	//in:nothing
	//out:nothing
	public void setmodeTank() {
		modeArcade = false;
	}

	//set mode to Fine//
	//in:nothing
	//out:nothing
	public void setmodeFine(){
		modeFine = true;
	}
	
	//set mode to Coarse//
	//in:nothing
	//out:nothing
	public void setmodeCoarse(){
		modeFine = false; 
	}
	
	//tell that mode is arcadeDrive//
	//in:nothing
	//out:modeArcade
	public boolean ismodeArcade(){
		return modeArcade;		
	}
	
	//tell that mode is tankDrive//
	//in:nothing
	//out:! modeArcade
	public boolean ismodeTank(){
		return ! modeArcade; 
	}
	
	//tell that mode is Fine//
	//in:nothing
	//out:modeFine
	public boolean ismodeFine(){
		return modeFine;
		
	}
	
	//tell that mode is Coarse//
	//in:nothing
	//out:nothing
	public boolean ismodeCoarse(){
		return !modeFine; 
	}
	
	//do the type of drive and how the drive work//
	//in:nothing
	//out:nothing
	public void doDriveType(){
	
		if(modeArcade == true && modeFine == true){
	    	myRobot.arcadeDrive(leftStick, squaredInputs);
	    }else if(modeArcade == false && modeFine == true){
	    	myRobot.tankDrive(leftStick, rightStick, squaredInputs);
	    }else if(modeArcade == true && modeFine == false){
	    	myRobot.arcadeDrive(leftStick);
	    }else{
	    	myRobot.tankDrive(leftStick, rightStick); 
	    }
	    
	}
	
	//Stop when there is something forward//
	//in:speed
	//out:nothing
	public void drivefowardunsafe(double speed){
		float distance = sensors.FrontSensors();
		if(distance==1 && speed>0){
				myRobot.drive(0,0);
			}
		}
	}
