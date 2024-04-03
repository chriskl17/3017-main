// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  private TalonFX indexer;
  public Indexer() {
    indexer = new TalonFX(IndexerConstants.kID);
  }
  public void runIndexer(double value){
    indexer.set(value);
  }
  public void stop(){
    indexer.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
