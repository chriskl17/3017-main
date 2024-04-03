// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private TalonFX frontLeft, backLeft, frontRight, backRight;
  public Shooter() {
    frontLeft = new TalonFX(ShooterConstants.kfrontLeftMotorID);
    backLeft = new TalonFX(ShooterConstants.KbackLeftMotorID);
    frontRight =  new TalonFX(ShooterConstants.kfrontRightMotorID);
    backRight = new TalonFX(ShooterConstants.kbackRightMotorID);

    frontLeft.setControl(new Follower(backLeft.getDeviceID(), false));
    frontRight.setControl(new Follower(backRight.getDeviceID(), false));
  }
  public void runShooter(double value){
    backLeft.set(value);
    backRight.set(-value);
  }
  public void stop(){
    backLeft.set(0.0);
    backRight.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
