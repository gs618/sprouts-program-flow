package com.github.gs618.sprouts.programflow;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author sgao
 */
@EqualsAndHashCode
public abstract class BaseStep implements Handler<Input, Output>, Serializable {

	private BaseStep nextStep;

	public BaseStep next(BaseStep nextStep) {
		this.nextStep = nextStep;
		return this.nextStep;
	}

	@Override
	public void start(Input input, Output output) {
		try {
			output.currentStep = this;
			handle(input);
			output.passedSteps.add(this);
		} catch (Exception e) {
			output.exception = e;
		}

		if (Objects.nonNull(output.exception)) {
			return;
		}

		Optional.ofNullable(nextStep).ifPresent(nextStep -> nextStep.start(input, output));
	}

	public abstract void handle(Input input);
}
