package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author sgao
 */
@EqualsAndHashCode(callSuper = false)
public class ChoiceStep extends Step {

	public static ChoiceStep newInstance() {
		return new ChoiceStep();
	}

	@Override
	protected void handle(Input input, Output output) {
		try {
			for (Map.Entry<Expression<Input>, Step> expressionBaseStepEntry : branches.entrySet()) {
				if (Boolean.TRUE.equals(expressionBaseStepEntry.getKey().compare(input))) {
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

	final Map<Expression<Input>, Step> branches = new HashMap<>();

	/**
	 * Add a choice
	 *
	 * @param expression the expression that is used to tell if next step should be active
	 * @param nextStep next step
	 * @return current choice step
	 */
	public ChoiceStep addChoice(Expression<Input> expression, Step nextStep) {
		branches.put(expression, nextStep);
		return this;
	}

}
