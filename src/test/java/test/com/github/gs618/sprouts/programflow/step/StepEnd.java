package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;

public class StepEnd extends Step {

	@Override
	public void handle(Input input, Output output) {
		System.out.println("this is the last step");
	}
}
