/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auton.TimedDrive;
import frc.robot.commands.intake.TimedIntakeIn;
import frc.robot.commands.shooter.TimedShooterOut;

public class ShootBallsFromTrench extends CommandGroup {
  public ShootBallsFromTrench() {
    addSequential(new TimedDrive(-0.5, 0.5));
    addSequential(new TimedDrive(0.5, 0.5));
    addParallel(new TimedShooterOut(5));
    addSequential(new TimedIntakeIn(4));

    //TODO: add code here for getting balls from trench
  }
}
