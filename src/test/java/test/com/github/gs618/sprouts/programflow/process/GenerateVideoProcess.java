package test.com.github.gs618.sprouts.programflow.process;

import com.github.gs618.sprouts.programflow.Process;
import com.github.gs618.sprouts.programflow.steps.ChoiceStep;
import com.github.gs618.sprouts.programflow.steps.LoopStep;
import com.github.gs618.sprouts.programflow.steps.ParallelStep;
import test.com.github.gs618.sprouts.programflow.step.StepAvi1080p;
import test.com.github.gs618.sprouts.programflow.step.StepAvi480p;
import test.com.github.gs618.sprouts.programflow.step.StepAvi720p;
import test.com.github.gs618.sprouts.programflow.step.StepAvi720pNext;
import test.com.github.gs618.sprouts.programflow.step.StepEnd;
import test.com.github.gs618.sprouts.programflow.step.StepFirst;
import test.com.github.gs618.sprouts.programflow.step.StepMkv1080p;
import test.com.github.gs618.sprouts.programflow.step.StepMkv480p;
import test.com.github.gs618.sprouts.programflow.step.StepMkv720p;
import test.com.github.gs618.sprouts.programflow.step.StepMp41080p;
import test.com.github.gs618.sprouts.programflow.step.StepMp4480p;
import test.com.github.gs618.sprouts.programflow.step.StepMp4720p;

public class GenerateVideoProcess extends Process {

	public GenerateVideoProcess() {
		super();
	}

	@Override
	public void build() {
		StepFirst stepFIrst = new StepFirst();
		ChoiceStep stepVideoTypeChecker = ChoiceStep.newInstance();
		StepAvi480p stepAvi480p = new StepAvi480p();
		StepAvi720p stepAvi720p = new StepAvi720p();
		StepAvi1080p stepAvi1080p = new StepAvi1080p();
		StepMkv480p stepMkv480p = new StepMkv480p();
		StepMkv720p stepMkv720p = new StepMkv720p();
		StepMkv1080p stepMkv1080p = new StepMkv1080p();
		StepMp4480p stepMp4480p = new StepMp4480p();
		StepMp4720p stepMp4720p = new StepMp4720p();
		StepMp41080p stepMp41080p = new StepMp41080p();
		ParallelStep stepAviParser = ParallelStep.newInstance();
		ParallelStep stepMkvParser = ParallelStep.newInstance();
		ParallelStep stepMp4Parser = ParallelStep.newInstance();
		StepEnd stepEnd = new StepEnd();
		LoopStep loopStepAvi720p = new LoopStep(stepAvi720p, 3);
		StepAvi720pNext stepAvi720pNext = new StepAvi720pNext();

		setFirstStep(stepFIrst);
		stepFIrst.next(stepVideoTypeChecker);
		stepVideoTypeChecker.addChoice(input -> "AVI".equalsIgnoreCase(input.getData("TYPE")), stepAviParser)
				.addChoice(input -> "MKV".equalsIgnoreCase(input.getData("TYPE")), stepMkvParser)
				.addChoice(input -> "MP4".equalsIgnoreCase(input.getData("TYPE")), stepMp4Parser);
		stepAvi720p.next(stepAvi720pNext);
		stepAviParser.addParallelBranch(stepAvi480p)
				.addParallelBranch(loopStepAvi720p)
				.addParallelBranch(stepAvi1080p)
				.next(stepEnd);
		stepMkvParser.addParallelBranch(stepMkv480p)
				.addParallelBranch(stepMkv720p)
				.addParallelBranch(stepMkv1080p)
				.next(stepEnd);
		stepMp4Parser.addParallelBranch(stepMp4480p)
				.addParallelBranch(stepMp4720p)
				.addParallelBranch(stepMp41080p)
				.next(stepEnd);
	}

}
