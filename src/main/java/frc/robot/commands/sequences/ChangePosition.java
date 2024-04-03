// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.RunPivot;

import frc.robot.subsystems.Pivot;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ChangePosition extends SequentialCommandGroup {
  /** Creates a new SourceIntake. */
  public ChangePosition(Pivot pivot, double setPoint) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new RunPivot(pivot, setPoint)
    );
  }
}
