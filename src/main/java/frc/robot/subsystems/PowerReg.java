// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerReg extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public PowerReg() {}

  static PowerDistribution PDP = new PowerDistribution(0, ModuleType.kCTRE);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  
  public static double getLimit(double mult) {
    double voltage = PDP.getVoltage();
    double current = PDP.getTotalCurrent();
    double limit = (mult * voltage);
    SmartDashboard.putNumber("VOLTAGE", voltage);
    SmartDashboard.putNumber("CURRENT", current);
    SmartDashboard.putNumber("IR", voltage/current);
    return limit;
  }
}
