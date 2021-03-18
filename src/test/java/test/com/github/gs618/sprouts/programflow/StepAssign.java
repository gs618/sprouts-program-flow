package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;

public class StepAssign extends BaseStep {

	@Override
	public void handle(Input input) {
		input.putData("THRESHOLD", input.getData("DATA"));
	}
}
