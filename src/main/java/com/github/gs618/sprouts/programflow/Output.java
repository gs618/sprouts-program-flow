package com.github.gs618.sprouts.programflow;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/** @author sgao */
@Getter
public class Output {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  final List<Step> passedSteps = new ArrayList<>(20);

  Step currentStep;

  @Setter
  Exception exception;

  /**
   * print step trace from output
   */
  public void printStepTrace() {
    Optional.ofNullable(exception).ifPresent(Exception::printStackTrace);

    if(passedSteps.isEmpty()) {
      return;
    }
    passedSteps.stream().map(step -> step.getClass().getName() + " ... succeed").forEach(logger::info);
    if (!currentStep.equals(passedSteps.get(passedSteps.size() - 1))) {
      logger.info(currentStep.getClass().getName() + " ... failure");
    }
  }
}
