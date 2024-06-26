// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private TalonFXConfiguration motorConfigDR;
  private TalonFXConfiguration motorConfigR;
  private TalonFXConfiguration motorConfigDL;
  private TalonFX frontRightD, frontLeftD, backRightD, backLeftD;
  private TalonFX frontRightR, frontLeftR, backRightR, backLeftR;
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    frontRightD = new TalonFX(1);
    frontRightR = new TalonFX(2);
    frontLeftD = new TalonFX(3);
    frontLeftR = new TalonFX(4);
    backRightD = new TalonFX(5);
    backRightR = new TalonFX(6);
    backLeftD = new TalonFX(7);
    backLeftR = new TalonFX(8);


    motorConfigDR = new TalonFXConfiguration();
    motorConfigDR.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motorConfigDR.Voltage.PeakForwardVoltage = 13.2;
    motorConfigDR.CurrentLimits.SupplyCurrentLimit = 60;
    motorConfigDR.CurrentLimits.SupplyCurrentLimitEnable = true;
    motorConfigDR.TorqueCurrent.PeakForwardTorqueCurrent = 100;
    motorConfigDR.TorqueCurrent.PeakReverseTorqueCurrent = -100;
    motorConfigDR.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    
    motorConfigDL = new TalonFXConfiguration();
    motorConfigDL.Voltage.PeakForwardVoltage = 13.2;
    motorConfigDL.CurrentLimits.SupplyCurrentLimit = 60;
    motorConfigDL.CurrentLimits.SupplyCurrentLimitEnable = true;
    motorConfigDL.TorqueCurrent.PeakForwardTorqueCurrent = 100;
    motorConfigDL.TorqueCurrent.PeakReverseTorqueCurrent = -100;
    motorConfigDL.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    // motorConfigR = new TalonFXConfiguration();
    // motorConfigR.Voltage.PeakForwardVoltage = 13.2;
    // motorConfigR.CurrentLimits.SupplyCurrentLimit = 60;
    // motorConfigR.CurrentLimits.SupplyCurrentLimitEnable = true;
    // motorConfigR.TorqueCurrent.PeakForwardTorqueCurrent = -266;
    // motorConfigR.TorqueCurrent.PeakReverseTorqueCurrent = -266; 
    // motorConfigR.MotorOutput.NeutralMode = NeutralModeValue.Brake;


    frontRightD.getConfigurator().apply(motorConfigDR);
    frontLeftD.getConfigurator().apply(motorConfigDL);
    backRightD.getConfigurator().apply(motorConfigDR);
    backLeftD.getConfigurator().apply(motorConfigDL);
    // frontRightR.getConfigurator().apply(motorConfigR);
    // frontLeftR.getConfigurator().apply(motorConfigR);
    // backRightR.getConfigurator().apply(motorConfigR);
    // backLeftR.getConfigurator().apply(motorConfigR);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
