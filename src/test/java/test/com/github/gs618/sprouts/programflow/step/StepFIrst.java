package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;

public class StepFIrst extends BaseStep {

	@Override
	public void handle(Input input, Output output) {
		System.out.println("File type is " + input.getData("TYPE"));
	}
}
