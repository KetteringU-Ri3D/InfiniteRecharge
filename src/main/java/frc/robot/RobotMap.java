/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // motor ports
  public static final int DRIVE_FRONT_LEFT_PORT = 2;
  public static final int DRIVE_REAR_LEFT_PORT = 4;
  public static final int DRIVE_FRONT_RIGHT_PORT = 1;
  public static final int DRIVE_REAR_RIGHT_PORT = 3;

  public static final int MISC_PORT = 5;
  public static final int SHOOTER_LEFT_PORT = 6;
  public static final int SHOOTER_RIGHT_PORT = 7;
  public static final int INTAKE_PORT = 8;
  public static final int CLIMBER_PORT = 9;
 
  // gamepad ports
  public static final int DRIVER_GAMEPAD_PORT = 0;
  public static final int OPERATOR_GAMEPAD_PORT = 1;

  // camera port
  public static final int CAMERA_PORT = 0;

  // IO Pins
  public static final int LED_PORT = 1;
}
