// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import java.util.logging.Logger;

import org.wpilib.driverstation.DefaultUserControls;
import org.wpilib.opmode.PeriodicOpMode;
import org.wpilib.opmode.Teleop;
import org.wpilib.smartdashboard.SmartDashboard;
import org.wpilib.system.DataLogManager;
import org.wpilib.datalog.*;

@Teleop
public class DefaultTeleMode extends PeriodicOpMode {
  private final Robot robot;
  private final DefaultUserControls userControls;
  private DoubleLogEntry joyStickLog;

  public DefaultTeleMode(Robot robot, DefaultUserControls userControls) {
    this.robot = robot;
    this.userControls = userControls;
  }

  @Override
  public void periodic() {
    if(userControls.getGamepad(0).getSouthFaceButton()){
robot.motor2.setAbsolutePosition(userControls.getGamepad(0).getRightY(), true);
    
    }else{
      robot.motor2.setThrottle(userControls.getGamepad(0).getLeftY());
    }
    SmartDashboard.putNumber("joystickValue", userControls.getGamepad(0).getLeftY());
    }
}
