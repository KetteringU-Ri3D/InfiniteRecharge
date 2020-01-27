/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.shooter.Shoot;
import frc.robot.commands.climber.ClimberDown;
import frc.robot.commands.climber.ClimberUp;
import frc.robot.commands.intake.IntakeIn;
import frc.robot.commands.intake.IntakeOut;
import frc.robot.utils.Gamepad;
import frc.robot.utils.JoystickAnalogButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  final int TRIGGER_LEFT = 2;
  final int TRIGGER_RIGHT = 3;

  Gamepad driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT);
  Gamepad operatorGamepad = new Gamepad(RobotMap.OPERATOR_GAMEPAD_PORT);

  JoystickAnalogButton operatorTriggerLeft = new JoystickAnalogButton(operatorGamepad, TRIGGER_LEFT);
  JoystickAnalogButton operatorTriggerRight = new JoystickAnalogButton(operatorGamepad, TRIGGER_RIGHT);

  public OI() {
    bindControls();
  }

  public void bindControls() {
    operatorGamepad.getLeftShoulder().toggleWhenPressed(new Shoot());
    operatorGamepad.getRightShoulder().whileHeld(new IntakeIn());
    operatorGamepad.getButtonA().whileHeld(new IntakeOut());
    // operatorTriggerRight.whileHeld(new IntakeOut());
    // operatorTriggerLeft.whileHeld(new SpinShooterOut());

    driverGamepad.getRightShoulder().whileHeld(new IntakeIn());
    driverGamepad.getButtonA().whileHeld(new IntakeOut());
    driverGamepad.getButtonX().whileHeld(new ClimberDown());
    driverGamepad.getButtonY().whileHeld(new ClimberUp());
  }
  
  public double getDriverGamepadLeftY() {
    double y = driverGamepad.getLeftY();
    return y;
  }

  public double getDriverGamepadRightY() {
    double y = driverGamepad.getRightY();
    return y;
  }

  public double getDriverGamepadRightX() {
    double x = driverGamepad.getRightX();
    return x;
  }
}
