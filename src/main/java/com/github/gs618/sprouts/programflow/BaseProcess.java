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

	public void run(Input input, Output output) {
		BaseStep baseStep = getFirstStep().run(input, output);
		while (Optional.ofNullable(baseStep).isPresent()) {
			if (Objects.nonNull(output.getException())) {
				break;
			}
			baseStep = baseStep.run(input, output);
		}
	}

}
