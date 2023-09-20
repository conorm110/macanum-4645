// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 2;
  private static final int kRearLeftChannel = 4;
  private static final int kFrontRightChannel = 5;
  private static final int kRearRightChannel = 3;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;

  @Override
  public void robotInit() {
    CANSparkMax frontLeft = new CANSparkMax(kFrontLeftChannel, MotorType.kBrushed);
    CANSparkMax rearLeft = new CANSparkMax(kRearLeftChannel, MotorType.kBrushed);
    CANSparkMax frontRight = new CANSparkMax(kFrontRightChannel, MotorType.kBrushed);
    CANSparkMax rearRight = new CANSparkMax(kRearRightChannel, MotorType.kBrushed);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(kJoystickChannel);
  }


  @Override
  public void teleopPeriodic() {
    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    if (m_stick.getRawButton(3)){
      if(Math.abs(-m_stick.getY()) > Math.abs(-m_stick.getX())) {
        m_robotDrive.driveCartesian(0, -m_stick.getY(), 0);
      } else {
        m_robotDrive.driveCartesian(-m_stick.getX(), 0, 0);
      }
    } else {
      m_robotDrive.driveCartesian(-m_stick.getX() *1.5,-m_stick.getY()*1.5, -m_stick.getZ() * 0.45);
    }
    
  }
}
