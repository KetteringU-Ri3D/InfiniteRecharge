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
 * Manipulator - used for picking Power Cells up and moving them
 */
public class Intake extends Subsystem {
  WPI_VictorSPX intakeMotor = new WPI_VictorSPX(RobotMap.INTAKE_PORT);  

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

  public void spinIntakeIn(double power) {
    intakeMotor.set(ControlMode.PercentOutput, -power);
  }

  public void spinIntakeOut(double power) {
    intakeMotor.set(ControlMode.PercentOutput, power);
  }

  public void stopIntake() {
    intakeMotor.stopMotor();
  }
}
