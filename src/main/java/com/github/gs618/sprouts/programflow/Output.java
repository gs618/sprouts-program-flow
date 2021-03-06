package com.github.gs618.sprouts.programflow;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** @author sgao */
@Getter
public class Output {

  List<BaseStep> passedSteps = new ArrayList<>(20);

  BaseStep currentStep;

  @Setter
  Exception exception;

}
