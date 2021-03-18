package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;

public class StepValueSmallerhanZero extends BaseStep {

	private static final long serialVersionUID = 2L;

	@Override
	public void handle(Input input) {
		System.out.println(input.getData("THRESHOLD").toString() + " < 0");
	}
}
