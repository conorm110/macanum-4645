// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Mecanum;

public class Drive extends CommandBase {
  private Mecanum mecanum;

  //CvSink cvSink = CameraServer.getVideo();

  //UsbCamera usbCamera = new UsbCamera("USB Camera 0", 4);
  //MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);

  // Creates the CvSink and connects it to the UsbCamera
  //CvSink cvSink = new CvSink("opencv_USB Camera 0");

  public Drive(Mecanum mecanum) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.mecanum = mecanum;
    addRequirements(this.mecanum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    CameraServer.startAutomaticCapture();
    // Creates UsbCamera and MjpegServer [1] and connects them
    //CameraServer.startAutomaticCapture();
    //mjpegServer1.setSource(usbCamera);
    //cvSink.setSource(usbCamera);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.mecanum.move();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.mecanum.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
