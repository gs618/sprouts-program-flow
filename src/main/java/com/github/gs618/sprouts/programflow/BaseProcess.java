package com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.exception.ProcessRuntimeException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author sgao
 */
@Data
@Accessors(chain = true)
public abstract class BaseProcess {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	protected BaseStep firstStep;

	public BaseProcess() {
		build();
	}

	/**
	 * Run a process
	 *
	 * @param input  input
	 * @param output output
	 */
	public void start(Input input, Output output) {
		if (Objects.isNull(getFirstStep())) {
			output.exception = new ProcessRuntimeException("First step is not provided");
			return;
		}
		BaseStep baseStep = getFirstStep().start(input, output);
		while (Objects.nonNull(baseStep) && Objects.isNull(output.getException())) {
			baseStep = baseStep.start(input, output);
		}
	}

	/**
	 * print step trace from output
	 *
	 * @param output output
	 */
	public void printStepTrace(Output output) {
		Optional.ofNullable(output.getException()).ifPresent(Exception::printStackTrace);

		if(output.getPassedSteps().isEmpty()) {
			return;
		}
		output.getPassedSteps().stream().map(step -> step.getClass().getName() + " ... succeed").forEach(logger::info);
		if (!output.getCurrentStep().equals(output.getPassedSteps().get(output.getPassedSteps().size() - 1))) {
			logger.info(output.getCurrentStep().getClass().getName() + " ... failure");
		}
	}

	/**
	 * build process
	 */
	public abstract void build();
}
