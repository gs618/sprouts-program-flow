package com.github.gs618.sprouts.programflow;

import lombok.EqualsAndHashCode;

/**
 * @author sgao
 */
@EqualsAndHashCode
public abstract class BaseStep {

	private BaseStep nextStep;

	public BaseStep next(BaseStep nextStep) {
		this.nextStep = nextStep;
		return this.nextStep;
	}

	public BaseStep run(Input input, Output output) {
		try {
			output.currentStep = this;
			handle(input, output);
			output.passedSteps.add(this);
		} catch (Exception e) {
			output.exception = e;
		}
		return nextStep;
	}

	/**
	 * What a step can do
	 *
	 * @param input input
	 * @param output output
	 */
	protected abstract void handle(Input input, Output output);
}
