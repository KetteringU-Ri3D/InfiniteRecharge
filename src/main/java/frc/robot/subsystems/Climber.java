/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climber - used to climb 
 */
public class Climber extends Subsystem {
  WPI_TalonSRX climberMotor = new WPI_TalonSRX(RobotMap.CLIMBER_PORT);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

  public void climberUp(double power) {
    climberMotor.set(ControlMode.PercentOutput, power);
  }

  public void climberDown(double power) {
    climberMotor.set(ControlMode.PercentOutput, -power);
  }

  public void stopClimber() {
    climberMotor.stopMotor();
  }
}
