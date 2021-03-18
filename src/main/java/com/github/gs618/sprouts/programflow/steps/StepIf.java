package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sgao
 */
public class StepIf extends BaseStep {

	@Override
	public void handle(Input input) {
		branches.forEach((key, value)->{
			if(key.compare(input)){
				next(value);
			}
		});
	}

	Map<Expression<Input>, BaseStep> branches = new HashMap<>();

	public void next(Expression<Input> expression, BaseStep nextStep){
		branches.put(expression, nextStep);
	}

}
