package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;

public class StepValueBiggerThanZeroSmallerThanHundred extends BaseStep {

	@Override
	public void handle(Input input) {
		System.out.println("0 < " + input.getData("THRESHOLD").toString() + " < 100");
	}
}
