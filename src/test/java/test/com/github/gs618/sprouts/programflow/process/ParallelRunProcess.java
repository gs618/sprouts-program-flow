package test.com.github.gs618.sprouts.programflow.process;

import com.github.gs618.sprouts.programflow.BaseProcess;
import com.github.gs618.sprouts.programflow.steps.StepParallel;
import test.com.github.gs618.sprouts.programflow.step.StepAssign;
import test.com.github.gs618.sprouts.programflow.step.StepEnd;
import test.com.github.gs618.sprouts.programflow.step.StepValueBiggerThanHundred;
import test.com.github.gs618.sprouts.programflow.step.StepValueBiggerThanZeroSmallerThanHundred;
import test.com.github.gs618.sprouts.programflow.step.StepValueSmallerhanZero;
import test.com.github.gs618.sprouts.programflow.step.StepWaiting;

public class ParallelRunProcess extends BaseProcess {

	public ParallelRunProcess() {
		super();
	}

	@Override
	public void build() {
		StepAssign stepAssign = new StepAssign();
		StepValueBiggerThanZeroSmallerThanHundred stepValueBiggerThanZeroSmallerThanHundred = new StepValueBiggerThanZeroSmallerThanHundred();
		StepValueSmallerhanZero stepValueSmallerhanZero = new StepValueSmallerhanZero();
		StepValueBiggerThanHundred stepValueBiggerThanHundred = new StepValueBiggerThanHundred();
		StepWaiting stepWaiting = new StepWaiting();
		StepParallel stepParallel = new StepParallel();
		StepEnd stepEnd = new StepEnd();

		setFirstStep(stepAssign);
		stepAssign.next(stepParallel).next(stepEnd);
		stepParallel.addParallelBranch(stepWaiting)
				.addParallelBranch(stepValueBiggerThanZeroSmallerThanHundred)
				.addParallelBranch(stepValueBiggerThanHundred);
		stepValueBiggerThanZeroSmallerThanHundred.next(stepValueSmallerhanZero);
	}

}
