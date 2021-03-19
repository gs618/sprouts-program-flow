package test.com.github.gs618.sprouts.programflow.process;

import com.github.gs618.sprouts.programflow.BaseProcess;
import com.github.gs618.sprouts.programflow.steps.StepChoice;
import test.com.github.gs618.sprouts.programflow.step.StepAssign;
import test.com.github.gs618.sprouts.programflow.step.StepValueBiggerThanHundred;
import test.com.github.gs618.sprouts.programflow.step.StepValueBiggerThanZeroSmallerThanHundred;
import test.com.github.gs618.sprouts.programflow.step.StepValueSmallerhanZero;

public class ChoiceProcess extends BaseProcess {

	public ChoiceProcess() {
		super();
	}

	@Override
	public void build() {
		StepAssign stepAssign = new StepAssign();
		StepValueBiggerThanZeroSmallerThanHundred stepValueBiggerThanZeroSmallerThanHundred = new StepValueBiggerThanZeroSmallerThanHundred();
		StepValueSmallerhanZero stepValueSmallerhanZero = new StepValueSmallerhanZero();
		StepValueBiggerThanHundred stepValueBiggerThanHundred = new StepValueBiggerThanHundred();
		StepChoice stepChoice0 = new StepChoice();
		StepChoice stepChoice1 = new StepChoice();

		setFirstStep(stepAssign);
		stepAssign.next(stepChoice0);

		stepChoice0.next(input -> Integer.parseInt(input.getData("THRESHOLD").toString()) > 0
				, stepChoice1);

		stepChoice0.next(input -> Integer.parseInt(input.getData("THRESHOLD").toString()) < 0
				, stepValueSmallerhanZero);

		stepChoice1.next(input -> Integer.parseInt(input.getData("THRESHOLD").toString()) > 100
				, stepValueBiggerThanHundred);

		stepChoice1.next(input -> Integer.parseInt(input.getData("THRESHOLD").toString()) < 100
				, stepValueBiggerThanZeroSmallerThanHundred);
	}

}
