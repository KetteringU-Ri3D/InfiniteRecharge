/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auton.TimedDrive;
import frc.robot.commands.auton.TimedTurn;
import frc.robot.commands.intake.TimedIntakeIn;
import frc.robot.commands.shooter.TimedShooterOut;

public class FiveBallRightAuto extends CommandGroup {
  /**
   * Eric's revised structure for a 5 ball Auton
   * Add your docs here.
   */
  public FiveBallRightAuto() {
    // Drops the intake down
    addSequential(new TimedDrive(-0.5, 0.5));
    addSequential(new TimedDrive(0.5, 0.5));

    // Shoot 3 balls ~4 seconds then turns
    addParallel(new TimedShooterOut(4)); // orig 5
    addSequential(new TimedIntakeIn(3.3)); // orig 4
    addSequential(new TimedTurn(0.75, 0.27)); // turn right
    // 4.5 seconds used

    // Must travel back ~8 feet then slow down
    addSequential(new TimedDrive(-0.75, 2)); // drive to near ball
    // 6.5 sec

    // Intake the balls
    addParallel(new TimedIntakeIn(4));
    addSequential(new TimedDrive(-0.5, 3)); // about correct distance
    // 9.5 sec

    // Code before
    //addParallel(new TimedDrive(0.6, 2.5));
    //addSequential(new TimedIntakeIn(1));
    // Code after
    addParallel(new TimeIntakeOut(1)); // Prevent ball from rolling out. Maybe increase
    addSequential(new TimedDrive(0.75, 3.5)); // drive back to line
    // 13 seconds used
    addParallel(new TimedShooterOut(5)); // Rev up shooter
    addSequential(new TimedTurn(-0.75, 0.15)); // turn left toward goal. Check angle

    // Shoot final balls
    //addParallel(new TimedShooterOut(5));
    addSequential(new TimedIntakeIn(4)); // hopefully score in time
    // 16 seconds?
  }
}