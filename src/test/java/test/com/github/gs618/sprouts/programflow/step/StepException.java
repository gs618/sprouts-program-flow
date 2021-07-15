package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;

public class StepException extends Step {

  @Override
  public void handle(Input input, Output output) {
    Integer a = 128;
    Integer b = 128;
    if (a != b) {
      throw new RuntimeException("Bad");
    }
  }
}
