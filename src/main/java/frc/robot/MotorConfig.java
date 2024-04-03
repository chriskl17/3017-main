// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MotorConfig extends InstantCommand {
  private TalonFXConfiguration motorConfig;
  private TalonFX frontRight, frontLeft, backRight, backLeft;
  public MotorConfig() {
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    frontRight = new TalonFX(1);
    frontLeft = new TalonFX(3);
    backRight = new TalonFX(5);
    backLeft = new TalonFX(7);

    motorConfig = new TalonFXConfiguration();
    motorConfig.Voltage.PeakForwardVoltage = 13.2;
    motorConfig.CurrentLimits.SupplyCurrentLimit = 60;
    motorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    frontRight.getConfigurator().apply(motorConfig);
    frontLeft.getConfigurator().apply(motorConfig);
    backRight.getConfigurator().apply(motorConfig);
    backLeft.getConfigurator().apply(motorConfig);
  }
}
