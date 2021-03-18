package com.github.gs618.sprouts.programflow;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sgao
 */
@Data
@Accessors(chain = true)
public abstract class BaseProcess implements Handler<Input, Output> {

	protected BaseStep firstStep;

	@Override
	public void start(Input input, Output output) {
		firstStep.start(input, output);
	}

}
