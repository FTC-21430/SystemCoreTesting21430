// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.driverstation.DefaultUserControls;
import org.wpilib.driverstation.UserControlsInstance;
import org.wpilib.framework.OpModeRobot;
import org.wpilib.hardware.expansionhub.ExpansionHubMotor;
import org.wpilib.hardware.expansionhub.ExpansionHubServo;
import org.wpilib.hardware.hal.CANBusMap;

import com.revrobotics.spark.A301;

/**
 * This is a demo program showing the use of the Expansion Hub motors and servos. The motors and
 * servos are driven using the controllers in the telop opmode, and timed in the auto op mode.
 */
@UserControlsInstance(DefaultUserControls.class)
public class Robot extends OpModeRobot {
  public final A301 motor1 = new A301(CANBusMap.CAN_D0);
  public final A301 motor2 = new A301(CANBusMap.CAN_D1);
  
  /** Called once at the beginning of the robot program. - INIT */
  public Robot() {}
}
