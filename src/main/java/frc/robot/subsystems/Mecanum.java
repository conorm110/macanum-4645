// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Mecanum extends SubsystemBase {
  public Joystick joystick = new Joystick(0);

  CANSparkMax upperLeft = new CANSparkMax(Constants.upperLeft, MotorType.kBrushed);
  CANSparkMax lowerLeft = new CANSparkMax(Constants.lowerLeft, MotorType.kBrushed);

  CANSparkMax upperRight = new CANSparkMax(Constants.upperRight, MotorType.kBrushed);
  CANSparkMax lowerRight = new CANSparkMax(Constants.lowerRight, MotorType.kBrushed);

  MecanumDrive mecanum = new MecanumDrive(upperLeft, lowerLeft, upperRight, lowerRight);

  public Mecanum() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move() {
    double forward = joystick.getY() * 0.5; // don't go too fast! multiply by 0.2. forward = speed
    double x = joystick.getX() * 0.5;
    double turn = joystick.getZ() * 0.5;

    mecanum.driveCartesian(forward, x, turn);
  }

  public void stop() {
    mecanum.driveCartesian(0, 0, 0);
  }
}
