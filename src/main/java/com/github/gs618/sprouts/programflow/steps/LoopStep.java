package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author sgao
 */
@EqualsAndHashCode(callSuper = false)
public class LoopStep extends Step {

	@Getter
	final Step firstStep;
	@Getter
	final int loopTimes;

	public LoopStep(Step firstStep, int loopTimes) {
		this.firstStep = firstStep;
		this.loopTimes = loopTimes;
	}

	@Override
	protected void handle(Input input, Output output) {
		try{
			if(Objects.isNull(firstStep)) {
				output.setException(new StepRuntimeException("Loop step is null"));
			}

			IntStream.range(0, loopTimes).boxed().forEach(i -> {
				Step nextStep = firstStep.start(input, output);
				while (Objects.nonNull(nextStep) && Objects.isNull(output.getException())) {
					nextStep = nextStep.start(input, output);
				}
			});
		} catch (Exception e) {
			throw new StepRuntimeException(e);
		}
	}

}
