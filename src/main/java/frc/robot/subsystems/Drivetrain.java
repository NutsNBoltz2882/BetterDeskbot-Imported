// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
//create the motor controller objects and put their ports in constants
  private static final PWMVictorSPX m_leftFront = new PWMVictorSPX(OperatorConstants.m_FRONTLEFTMOTOR);
  private static final PWMVictorSPX m_leftBack = new PWMVictorSPX(OperatorConstants.m_BACKLEFTMOTOR);
  private static final PWMVictorSPX m_rightFront = new PWMVictorSPX(OperatorConstants.m_FRONTRIGHTMOTOR);
  private static final PWMVictorSPX m_rightBack = new PWMVictorSPX(OperatorConstants.m_BACKRIGHTMOTOR);

  //pass them into mecanum drive object
  private static final MecanumDrive m_robotDrive = new MecanumDrive(m_rightFront, m_rightBack, m_leftFront, m_leftBack);

  //create driver controller
  private static final XboxController driverController = new XboxController(0);

  public Drivetrain() {
    //set left motors to be inverted so robot no spin
    m_leftFront.setInverted(true);
    m_leftBack.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //driveCartesian does all the work for u, just pass in the vals from driver controller and multiply by a decimal to slow it down
    m_robotDrive.driveCartesian(driverController.getLeftY() * .37, driverController.getLeftX() * .32,
        driverController.getRightX() * .4);
  }
}
