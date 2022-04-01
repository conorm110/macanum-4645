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

  

  public void translate(double x) {
    // note: these need to be 0, -x, or x
    // upperLeft.set(x);
    // upperRight.set(x);
    //lowerLeft.set(x);
    //lowerRight.set(x);
  }

  public void move() {
    // autolimits
    double fdLim = PowerReg.getLimit(0.09);
    double xLim = PowerReg.getLimit(0.09);
    double tnLim  = 0.25 * PowerReg.getLimit(0.09);
    
    // precision driving mode
    if (joystick.getRawButton(1)) {
      fdLim = 0.4;
      xLim = 0.4;
    }

    double forward = joystick.getZ() * -1 * tnLim; // dturns, dont ask
    double x = joystick.getX() * xLim; // still get x, wont be used in driveCartesian
    double turn = joystick.getY() * fdLim; // moves forward, dont ask

    mecanum.driveCartesian(forward, 0, turn); // there are issues with translating, dont use in driveCartesian
    translate(x); // manual translate function
  }

  public void stop() {
    mecanum.driveCartesian(0, 0, 0);
  }
}
