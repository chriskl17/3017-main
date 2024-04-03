// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PivotConstants;

public class Pivot extends SubsystemBase {
  /** Creates a new Pivot. */
  private TalonFX rightPivot, leftPivot;
  private PIDController pivotController;
  private CANcoder canCoder;
  private TalonFXConfiguration pivotConfig;
  public Pivot() {
    rightPivot = new TalonFX(PivotConstants.kRightID);
    leftPivot = new TalonFX(PivotConstants.kLeftID);
    leftPivot.setControl(new Follower(rightPivot.getDeviceID(), true));
    
    pivotConfig = new TalonFXConfiguration();
    pivotConfig.Slot0.GravityType = GravityTypeValue.Arm_Cosine;
    pivotConfig.Slot0.kG = 0.035;
    pivotConfig.Slot0.kP = 10;
    pivotConfig.Slot0.kS = 0.039;
    pivotConfig.Slot0.kV = 0.08;
    pivotConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    pivotConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    pivotConfig.Feedback.FeedbackRemoteSensorID = PivotConstants.kpivotCanCoderID;
    pivotConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
    pivotConfig.MotionMagic.MotionMagicAcceleration = 1.4;
    pivotConfig.MotionMagic.MotionMagicCruiseVelocity = 1;
    rightPivot.getConfigurator().apply(pivotConfig);

    pivotController = new PIDController(3.4, 0, 0);
    canCoder = new CANcoder(PivotConstants.kpivotCanCoderID);
    pivotController.setTolerance(PivotConstants.kTolarance);
    
  }
  public void goTo(double setPoint){
    // rightPivot.set(pivotController.calculate(getPosition(), setPoint));
    rightPivot.setControl(new MotionMagicDutyCycle(setPoint));
  }
  public double getPosition(){
    return canCoder.getAbsolutePosition().getValueAsDouble();
  }
  public void runPivot(double value){
    rightPivot.set(value);
  }
  public void stop(){
    rightPivot.setControl(new NeutralOut());
  }
  public boolean atSetpoint(){
    return pivotController.atSetpoint();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("CANCoder", getPosition());
  }
}
