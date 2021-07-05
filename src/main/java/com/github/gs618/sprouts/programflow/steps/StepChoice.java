package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author sgao
 */
public class StepChoice extends BaseStep {

	public static StepChoice newInstance() {
		return new StepChoice();
	}

	@Override
	public void handle(Input input, Output output) {
		try {
			for (Map.Entry<Expression<Input>, BaseStep> expressionBaseStepEntry : branches.entrySet()) {
				if (expressionBaseStepEntry.getKey().compare(input)) {
					next(expressionBaseStepEntry.getValue());
					break;
				}
			}
			if(Objects.isNull(getNextStep())){
				output.setException(new StepRuntimeException("None of the options meet the conditions"));
			}
		} catch (Exception e) {
			throw new StepRuntimeException(e);
		}
	}

	Map<Expression<Input>, BaseStep> branches = new HashMap<>();

	public StepChoice addChoice(Expression<Input> expression, BaseStep nextStep) {
		branches.put(expression, nextStep);
		return this;
	}

}
