/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.DelayedShootAuto;
import frc.robot.auto.FiveBallLeftAuto;
import frc.robot.auto.FiveBallRightAuto;
import frc.robot.auto.ShootAuto;
import frc.robot.commands.leds.AlternatingBlueYellow;
import frc.robot.commands.leds.BlueWhiteTrail;
import frc.robot.commands.leds.BlueYellowTrail;
import frc.robot.commands.leds.Flint;
import frc.robot.commands.leds.RainbowFade;
import frc.robot.commands.leds.RandomColors;
import frc.robot.commands.leds.SolidBlue;
import frc.robot.commands.leds.SolidYellow;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.LED;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain drivetrain = new Drivetrain();
  public static Intake intake = new Intake();
  public static Shooter shooter = new Shooter();
  public static Climber climber = new Climber();
  public static OI oi;
  public static LED leds = new LED();

  Command m_autonomousCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();
  SendableChooser<Command> ledChooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer camera = CameraServer.getInstance();
    camera.startAutomaticCapture(RobotMap.CAMERA_PORT);

    oi = new OI();

    // autoChooser.setDefaultOption("Shoot Auto", new ShootAuto());
    // autoChooser.setDefaultOption("Five Ball Auto", new FiveBallLeftAuto());
    autoChooser.setDefaultOption("Five Ball Right Auto", new FiveBallRightAuto());
    autoChooser.addOption("Delayed Shoot Auto", new DelayedShootAuto());
    // autoChooser.addOption("Five Ball Auto", new FiveBallAuto());
    SmartDashboard.putData("Auto Mode", autoChooser);

    ledChooser.setDefaultOption("Solid Blue", new SolidBlue());
    ledChooser.addOption("Solid Yellow", new SolidYellow());
    ledChooser.addOption("Blue/Yellow Trail", new BlueYellowTrail());
    ledChooser.addOption("Blue/White Trail", new BlueWhiteTrail());
    ledChooser.addOption("Blue and Yellow", new AlternatingBlueYellow());
    ledChooser.addOption("Rainbow", new RainbowFade());
    ledChooser.addOption("Random Colors", new RandomColors());
    ledChooser.addOption("Flint", new Flint());
    SmartDashboard.putData("LED setting", ledChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = autoChooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
