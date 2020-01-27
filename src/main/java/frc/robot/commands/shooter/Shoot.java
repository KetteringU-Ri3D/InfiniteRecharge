/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.intake.IntakeIn;
import frc.robot.commands.intake.TimedIntakeOut;

public class Shoot extends CommandGroup {
  public Shoot() {
    addSequential(new TimedIntakeOut(0.5));
    // addParallel(new TimedShooterUp(0.1));
    addParallel(new SpinShooterOut());
    addParallel(new IntakeIn());
  }
}
