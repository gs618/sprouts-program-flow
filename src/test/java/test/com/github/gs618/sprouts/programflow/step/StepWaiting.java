package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;

public class StepWaiting extends BaseStep {

	@Override
	public void handle(Input input, Output output) {
		try {
			System.out.println("Wait for 5 seconds");
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			throw new StepRuntimeException(e);
		}
	}
}
