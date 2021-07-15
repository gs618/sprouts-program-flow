package com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.exception.ProcessRuntimeException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * A process is a basic unit for our business logic. It is composed by a lot of steps.
 * So far, a process is not reusable, and it is like a piece of standalone code.
 *
 * @author sgao
 */
@Data
@Accessors(chain = true)
public abstract class Process {

	protected Step firstStep;

	protected Process() {
		build();
	}

	/**
	 * Start a process
	 *
	 * @param input  input
	 * @param output output
	 */
	public void start(Input input, Output output) {
		if (Objects.isNull(getFirstStep())) {
			output.exception = new ProcessRuntimeException("First step is not provided");
			return;
		}
		Step nextStep = getFirstStep().start(input, output);
		while (Objects.nonNull(nextStep) && Objects.isNull(output.exception)) {
			nextStep = nextStep.start(input, output);
		}
	}

	/**
	 * build process
	 */
	public abstract void build();
}
