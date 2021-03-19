package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sgao
 */
public class StepIf extends BaseStep {

	@Override
	public void handle(Input input) {
		try {
			for (Map.Entry<Expression<Input>, BaseStep> expressionBaseStepEntry : branches.entrySet()) {
				if (expressionBaseStepEntry.getKey().compare(input)) {
					next(expressionBaseStepEntry.getValue());
				}
			}
		} catch (Exception e) {
			throw new StepRuntimeException(e);
		}
	}

	Map<Expression<Input>, BaseStep> branches = new HashMap<>();

	public void next(Expression<Input> expression, BaseStep nextStep) {
		branches.put(expression, nextStep);
	}

}
