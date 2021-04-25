package com.github.gs618.sprouts.programflow;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Optional;

/**
 * @author sgao
 */
@Data
@Accessors(chain = true)
public abstract class BaseProcess {

	protected BaseStep firstStep;

	public BaseProcess() {
		build();
	}

	/**
	 * Run a process
	 *
	 * @param input input
	 * @param output output
	 */
	public void run(Input input, Output output) {
		BaseStep baseStep = getFirstStep().run(input, output);
		while (Objects.nonNull(baseStep)) {
			if (Objects.isNull(output.getException())) {
				baseStep = baseStep.run(input, output);
			}
		}
	}

	/**
	 * print step trace from output
	 *
	 * @param output output
	 */
	public void printStepTrace(Output output){
		Optional.ofNullable(output.getException()).ifPresent(Exception::printStackTrace);
		output.getPassedSteps().stream().map(step->step.getClass().getName() + " ... succeed").forEach(System.out::println);

		if(!output.getCurrentStep().equals(output.getPassedSteps().get(output.getPassedSteps().size() - 1))) {
			System.out.println(output.getCurrentStep().getClass().getName() + " ... failure");
		}
	}

	/**
	 * build process
	 */
	public abstract void build();
}
