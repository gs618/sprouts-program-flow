package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.BaseProcess;
import com.github.gs618.sprouts.programflow.steps.StepIf;

import java.util.Objects;

public class ProcessTest extends BaseProcess {

	public ProcessTest() {
		StepAssign stepAssign = new StepAssign();
		StepValueBiggerThanZero stepValueBiggerThanZero = new StepValueBiggerThanZero();
		StepValueSmallerhanZero stepValueSmallerhanZero = new StepValueSmallerhanZero();
		StepIf stepIf = new StepIf();

		firstStep = stepAssign;
		stepAssign.next(stepIf);

		stepIf.next(input -> {
					Object threshold = input.getData("THRESHOLD");
					if (Objects.nonNull(threshold)) {
						return Integer.parseInt(threshold.toString()) > 0;
					} else {
						return false;
					}
				}
				, stepValueBiggerThanZero);

		stepIf.next(input -> {
					Object threshold = input.getData("THRESHOLD");
					if (Objects.nonNull(threshold)) {
						return Integer.parseInt(threshold.toString()) < 0;
					} else {
						return false;
					}
				}
				, stepValueSmallerhanZero);
	}


}
