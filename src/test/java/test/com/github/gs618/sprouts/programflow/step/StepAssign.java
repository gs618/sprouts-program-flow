package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;

public class StepAssign extends BaseStep {

	@Override
	public void handle(Input input, Output output) {
		input.putData("THRESHOLD", input.getData("DATA"));
	}
}
