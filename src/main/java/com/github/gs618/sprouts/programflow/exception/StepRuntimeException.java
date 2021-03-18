package com.github.gs618.sprouts.programflow.exception;

/**
 * @author sgao
 */
public class StepRuntimeException extends RuntimeException {

	public StepRuntimeException(){}

	public StepRuntimeException(Exception e) {
		super(e);
	}
}
