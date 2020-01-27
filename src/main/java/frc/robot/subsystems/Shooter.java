/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Shooter - used for scoring Power Cells
 */
public class Shooter extends Subsystem {
  WPI_VictorSPX shooterLeftMotor = new WPI_VictorSPX(RobotMap.SHOOTER_LEFT_PORT);
  WPI_VictorSPX shooterRightMotor = new WPI_VictorSPX(RobotMap.SHOOTER_RIGHT_PORT);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

  public void spinShooterOut(double power) {
    shooterLeftMotor.set(ControlMode.PercentOutput, -power);
    shooterRightMotor.set(ControlMode.PercentOutput, power);
  }

  public void spinShooterIn(double power) {
    shooterLeftMotor.set(ControlMode.PercentOutput, power);
    shooterRightMotor.set(ControlMode.PercentOutput, -power);
  }

  public void stopShooter() {
    shooterLeftMotor.stopMotor();
    shooterRightMotor.stopMotor();
  }
}
