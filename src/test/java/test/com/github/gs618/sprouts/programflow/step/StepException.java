package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;

public class StepException extends BaseStep {

	@Override
	public void handle(Input input) {
		int i = 1/0;
	}
}
