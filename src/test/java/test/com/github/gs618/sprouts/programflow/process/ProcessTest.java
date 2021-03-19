package test.com.github.gs618.sprouts.programflow.process;

import com.github.gs618.sprouts.programflow.BaseProcess;
import com.github.gs618.sprouts.programflow.steps.StepIf;
import test.com.github.gs618.sprouts.programflow.step.StepAssign;
import test.com.github.gs618.sprouts.programflow.step.StepException;
import test.com.github.gs618.sprouts.programflow.step.StepValueBiggerThanZero;
import test.com.github.gs618.sprouts.programflow.step.StepValueSmallerhanZero;

public class ProcessTest extends BaseProcess {

	public ProcessTest() {
		super();
	}

	@Override
	public void build() {
		StepAssign stepAssign = new StepAssign();
		StepValueBiggerThanZero stepValueBiggerThanZero = new StepValueBiggerThanZero();
		StepValueSmallerhanZero stepValueSmallerhanZero = new StepValueSmallerhanZero();
		StepException stepException = new StepException();
		StepIf stepIf = new StepIf();

		setFirstStep(stepAssign);
		stepAssign.next(stepIf);
		stepException.next(stepValueBiggerThanZero);

		stepIf.next(input -> Integer.parseInt(input.getData("THRESHOLD").toString()) > 0
				, stepException);

		stepIf.next(input -> Integer.parseInt(input.getData("THRESHOLD").toString()) < 0
				, stepValueSmallerhanZero);
	}

}
