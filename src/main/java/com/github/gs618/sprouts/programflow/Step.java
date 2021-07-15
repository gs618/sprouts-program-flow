package com.github.gs618.sprouts.programflow;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Base step is the smallest unit for the code piece, and it could be reusable.
 * The step should be in a sync code normally, and do only one single thing.
 *
 * @author sgao
 */
@EqualsAndHashCode
public abstract class Step {

	@Getter
	Step nextStep;

	/**
	 * define what next step is
	 *
	 * @param nextStep next step
	 * @return next step
	 */
	public Step next(Step nextStep) {
		this.nextStep = nextStep;
		return this.nextStep;
	}

	/**
	 * start a step
	 *
	 * @param input input
	 * @param output output
	 * @return next step
	 */
	public Step start(Input input, Output output) {
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
	 * @param input  input
	 * @param output output
	 */
	protected abstract void handle(Input input, Output output);
}
