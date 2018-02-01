package org.usfirst.frc.team5229.robot;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Joystick;
import java.util.Scanner;

public class Climbing {
	
	private PWM m_climbMotor;

	private boolean setMotor = false;
	private Sensors topSwitch = new Sensors();
	private Sensors bottomSwitch = new Sensors();
	
	public void setSwitches(int dio) {
		topSwitch.limitswitch(dio);
		bottomSwitch.limitswitch(dio);
	}
	
	
	public boolean setClimbMotor (PWM m_climbMotorIn) {
		m_climbMotor = m_climbMotorIn;
		setMotor = true;
		return setMotor;
	}
	
	public void raiseElevator (double speed) {
		//Raises elevator to hook bar 
		//Spins motor forward (Speed = +)
		
		boolean sensorpressed = topSwitch.getstate(); 
		
		if (!setMotor) {
			System.out.println("ERROR: Climb Motor Not Initiated!");
		}
		
		else {
			while(sensorpressed) {
				m_climbMotor.setSpeed(speed);
			}
		}
	}
	
	
	//Raises Robot
	//Input of distance in motor rotations
	public void lowerElavator (double speed) {
		//pull up robot using hook attached to bar
		//Spins motor backwards (Speed = -)
		boolean sensorpressed = bottomSwitch.getstate(); 
		if (!setMotor) {
			System.out.println("ERROR: Climb Motor Not Initiated!");
		}
		
		else {
			while(sensorpressed) {
				m_climbMotor.setSpeed(-speed);
			}
		}
	}
}
	

